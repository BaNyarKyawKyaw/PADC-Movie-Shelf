package com.bnkk.padcmovieshelf.dagger;

import android.content.Context;

import com.bnkk.padcmovieshelf.MovieApp;
import com.bnkk.padcmovieshelf.data.models.MovieModel;
import com.bnkk.padcmovieshelf.mvp.presenters.MovieListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by E5-575G on 1/9/2018.
 */

@Module
public class AppModule {

    private MovieApp mApp;

    public AppModule(MovieApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public MovieModel provideMovieModel(Context context) {
        return new MovieModel(context);
    }

    @Provides
    public MovieListPresenter provideMovieListPresenter() {
        return new MovieListPresenter();
    }
}
