package com.bnkk.padcmovieshelf.network;

import com.bnkk.padcmovieshelf.events.RestApiEvents;
import com.bnkk.padcmovieshelf.network.responses.GetPopularMoviesResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieDataAgentImpl implements MovieDataAgent {

    private static MovieDataAgentImpl objInstance;

    private MovieAPI theAPI;

    private MovieDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/popular-movies/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(MovieAPI.class);
    }

    public static MovieDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new MovieDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadMovies(String accessToken, int page) {
        Call<GetPopularMoviesResponse> loadMovieCall = theAPI.loadPopularMovies(accessToken, page);
        loadMovieCall.enqueue(new Callback<GetPopularMoviesResponse>() {
            @Override
            public void onResponse(Call<GetPopularMoviesResponse> call, Response<GetPopularMoviesResponse> response) {
                GetPopularMoviesResponse getPopularMoviesResponse = response.body();
                if (getPopularMoviesResponse != null && getPopularMoviesResponse.getPopularMovies().size() > 0) {
                    RestApiEvents.MovieDataLoadedEvent movieDataLoadedEvent = new RestApiEvents.MovieDataLoadedEvent
                            (getPopularMoviesResponse.getPage(), getPopularMoviesResponse.getPopularMovies());
                    EventBus.getDefault().post(movieDataLoadedEvent);
                } else {
                    RestApiEvents.ErrorInvokingAPIEvent errorEvent
                            = new RestApiEvents.ErrorInvokingAPIEvent("No data could be loaded for now. Pls try again later");
                    EventBus.getDefault().post(errorEvent);
                }
            }

            @Override
            public void onFailure(Call<GetPopularMoviesResponse> call, Throwable t) {
                RestApiEvents.ErrorInvokingAPIEvent errorEvent
                        = new RestApiEvents.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
