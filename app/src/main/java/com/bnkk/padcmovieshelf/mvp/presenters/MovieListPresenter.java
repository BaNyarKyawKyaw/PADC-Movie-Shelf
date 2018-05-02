package com.bnkk.padcmovieshelf.mvp.presenters;

import android.content.Context;
import android.database.Cursor;

import com.bnkk.padcmovieshelf.MovieApp;
import com.bnkk.padcmovieshelf.data.models.MovieModel;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.delegates.MovieItemDelegate;
import com.bnkk.padcmovieshelf.mvp.views.MovieListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by E5-575G on 1/9/2018.
 */

public class MovieListPresenter extends BasePresenter<MovieListView>
        implements MovieItemDelegate {

    @Inject
    MovieModel mMovieModel;

    public MovieListPresenter() {

    }

    @Override
    public void onCreate(MovieListView view) {
        super.onCreate(view);
        MovieApp movieApp = (MovieApp) mView.getContext();
        movieApp.getAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        mView.showLoading();
    }

    @Override
    public void onStop() {

    }

    public void onMovieListEndReach(Context context) {
        mMovieModel.loadMoreMovies(context);
    }

    public void onForceRefresh(Context context) {
        mMovieModel.forceRefreshNews(context);
    }

    public void onDataLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<MovieVO> movieList = new ArrayList<>();

            do {
                MovieVO movie = MovieVO.parseFromCursor(context, data);
                movieList.add(movie);
            } while (data.moveToNext());

            mView.displayMovieList(movieList);
        } else {
            mMovieModel.startLoadingMovies(context);
        }
    }

    @Override
    public void onTapMovie(MovieVO movie) {
        mView.navigateToMovieDetails(movie);
    }

    @Override
    public void onTapMovieOverview() {

    }
}
