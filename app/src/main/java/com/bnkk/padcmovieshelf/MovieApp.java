package com.bnkk.padcmovieshelf;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bnkk.padcmovieshelf.dagger.AppComponent;
import com.bnkk.padcmovieshelf.dagger.AppModule;
import com.bnkk.padcmovieshelf.dagger.DaggerAppComponent;
import com.bnkk.padcmovieshelf.dagger.NetworkModule;
import com.bnkk.padcmovieshelf.dagger.UtilsModule;
import com.bnkk.padcmovieshelf.data.models.MovieModel;
import com.bnkk.padcmovieshelf.utils.ConfigUtils;

import javax.inject.Inject;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieApp extends Application {

    public static final String LOG_TAG = "MovieApp";

    private AppComponent mAppComponent;

    @Inject
    Context mContext;

    @Inject
    MovieModel mMovieModel;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = initDagger();
        mAppComponent.inject(this);

        Log.d(LOG_TAG, "mContext:" + mContext);
    }

    private AppComponent initDagger() {
        //return null;
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .utilsModule(new UtilsModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
