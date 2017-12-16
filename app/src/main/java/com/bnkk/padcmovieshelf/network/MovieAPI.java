package com.bnkk.padcmovieshelf.network;

import com.bnkk.padcmovieshelf.network.responses.GetPopularMoviesResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by E5-575G on 12/12/2017.
 */

public interface MovieAPI {

    @FormUrlEncoded
    @POST("v1/getPopularMovies.php")
    Call<GetPopularMoviesResponse> loadPopularMovies(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex);
}
