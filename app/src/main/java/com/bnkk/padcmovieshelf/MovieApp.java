package com.bnkk.padcmovieshelf;

import android.app.Application;

import com.bnkk.padcmovieshelf.data.models.MovieModel;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieApp extends Application {

    public static final String LOG_TAG = "MovieApp";

    @Override
    public void onCreate() {
        super.onCreate();
        MovieModel.getObjInstance().startLoadingMovies();
    }
}
