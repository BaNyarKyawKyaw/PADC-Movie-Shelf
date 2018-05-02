package com.bnkk.padcmovieshelf.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.bnkk.padcmovieshelf.MovieApp;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.events.RestApiEvents;
import com.bnkk.padcmovieshelf.network.MovieDataAgent;
import com.bnkk.padcmovieshelf.persistence.MovieContract;
import com.bnkk.padcmovieshelf.utils.AppConstants;
import com.bnkk.padcmovieshelf.utils.ConfigUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieModel {

    //private List<MovieVO> mMoviesList;

    @Inject
    MovieDataAgent mDataAgent;

    @Inject
    ConfigUtils mConfigUtils;

    public MovieModel(Context context) {
        EventBus.getDefault().register(this);

        MovieApp movieApp = (MovieApp) context.getApplicationContext();
        movieApp.getAppComponent().inject(this);
    }

    public void startLoadingMovies(Context context) {
        mDataAgent.loadMovies(AppConstants.ACCESS_TOKEN,
                mConfigUtils.loadPageIndex(),
                context);
    }

    public void loadMoreMovies(Context context) {
        mDataAgent.loadMovies(AppConstants.ACCESS_TOKEN,
                mConfigUtils.loadPageIndex(),
                context);
    }

    public void forceRefreshNews(Context context) {
        mConfigUtils.savePageIndex(1);
        startLoadingMovies(context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMovieDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
        mConfigUtils.savePageIndex(event.getLoadedPageIndex() + 1);

        // Logic for saving data in Persistence Layer
        ContentValues[] movieCVs = new ContentValues[event.getLoadedMovies().size()];
        List<ContentValues> genreCVList = new ArrayList<>();
        List<ContentValues> movieGenreCVList = new ArrayList<>();

        for (int index = 0; index < movieCVs.length; index++) {

            MovieVO movies = event.getLoadedMovies().get(index);
            movieCVs[index] = movies.parseToContentValues();

            for (int genreId : movies.getGenreIds()) {
                ContentValues genreIdInMovieCV = new ContentValues();
                genreIdInMovieCV.put(MovieContract.GenreEntry.COLUMN_GENRE_ID, genreId);
                genreCVList.add(genreIdInMovieCV);
            }

            for (int i = 0; i < movies.getGenreIds().size(); i++) {
                ContentValues movieGenreCV = new ContentValues();
                movieGenreCV.put(MovieContract.MovieGenreEntry.COLUMN_GENRE_ID, String.valueOf(movies.getGenreIds()));
                movieGenreCV.put(MovieContract.MovieGenreEntry.COLUMN_MOVIE_ID, movies.getMovieId());
                movieGenreCVList.add(movieGenreCV);
            }
        }

        int insertedGenre = event.getContext().getContentResolver().bulkInsert(MovieContract.GenreEntry.CONTENT_URI,
                genreCVList.toArray(new ContentValues[0]));
        Log.d(MovieApp.LOG_TAG, "insertedGenre" + insertedGenre);

        int insertedMovieGenre = event.getContext().getContentResolver().bulkInsert(MovieContract.MovieGenreEntry.CONTENT_URI,
                movieGenreCVList.toArray(new ContentValues[0]));
        Log.d(MovieApp.LOG_TAG, "insertedMovieGenre" + insertedMovieGenre);

        int insertedMovies = event.getContext().getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI,
                movieCVs);
        Log.d(MovieApp.LOG_TAG, "Inserted News" + insertedMovies);
    }
}
