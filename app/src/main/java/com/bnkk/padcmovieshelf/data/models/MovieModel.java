package com.bnkk.padcmovieshelf.data.models;

import android.util.ArrayMap;
import android.util.ArraySet;

import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.events.RestApiEvents;
import com.bnkk.padcmovieshelf.network.MovieDataAgentImpl;
import com.bnkk.padcmovieshelf.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    public void startLoadingMovies() {
        MovieDataAgentImpl.getObjInstance().loadMovies(AppConstants.ACCESS_TOKEN, mMoviePageIndex);
    }

    public List<MovieVO> getMovies() {
        if (mMoviesSet == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(mMoviesSet.values());
    }

    public void loadMoreMovies() {
        MovieDataAgentImpl.getObjInstance().loadMovies(AppConstants.ACCESS_TOKEN, mMoviePageIndex);
    }

    public void forceRefreshNews() {
        mMoviesSet = new HashMap<>();
        mMoviePageIndex = 1;
        startLoadingMovies();
    }

    @Subscribe
    public void onMovieDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
        mMoviePageIndex = event.getLoadedPageIndex() + 1;

        for (MovieVO movieVO : event.getLoadedMovies()) {
            mMoviesSet.put(String.valueOf(movieVO.getMovieId()), movieVO);
        }
    }
}
