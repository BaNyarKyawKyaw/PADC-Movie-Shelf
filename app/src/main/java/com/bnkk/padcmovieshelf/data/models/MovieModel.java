package com.bnkk.padcmovieshelf.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;

import com.bnkk.padcmovieshelf.MovieApp;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.events.RestApiEvents;
import com.bnkk.padcmovieshelf.network.MovieDataAgentImpl;
import com.bnkk.padcmovieshelf.persistence.MovieContract;
import com.bnkk.padcmovieshelf.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieModel {

    private static MovieModel objInstance;

    private Map<String, MovieVO> mMoviesSet;
    private int mMoviePageIndex = 1;

    private MovieModel() {
        EventBus.getDefault().register(this);
        mMoviesSet = new HashMap<>();
    }

    public static MovieModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public void startLoadingMovies(Context context) {
        MovieDataAgentImpl.getObjInstance().loadMovies(AppConstants.ACCESS_TOKEN, mMoviePageIndex, context);
    }

    public List<MovieVO> getMovies() {
        if (mMoviesSet == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(mMoviesSet.values());
    }

    public void loadMoreMovies(Context context) {
        MovieDataAgentImpl.getObjInstance().loadMovies(AppConstants.ACCESS_TOKEN, mMoviePageIndex, context);
    }

    public void forceRefreshNews(Context context) {
        mMoviesSet = new HashMap<>();
        mMoviePageIndex = 1;
        startLoadingMovies(context);
    }

    @Subscribe
    public void onMovieDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {

        mMoviePageIndex = event.getLoadedPageIndex() + 1;
        // inserting data into mMovieSet
        for (MovieVO movieVO : event.getLoadedMovies()) {
            mMoviesSet.put(String.valueOf(movieVO.getMovieId()), movieVO);
        }

        ContentValues[] movieCVs = new ContentValues[event.getLoadedMovies().size()];
        for (int index = 0; index < movieCVs.length; index++) {
            movieCVs[index] = event.getLoadedMovies().get(index).parseToContentValues();
        }

        int insertedRows = event.getContext().getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI,
                movieCVs);

        Log.d(MovieApp.LOG_TAG, "Inserted Rows" + insertedRows);
    }
}
