package com.bnkk.padcmovieshelf.dagger;

import com.bnkk.padcmovieshelf.MovieShelfApp;
import com.bnkk.padcmovieshelf.activities.MovieListActivity;
import com.bnkk.padcmovieshelf.data.models.MovieModel;
import com.bnkk.padcmovieshelf.mvp.presenters.MovieListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by E5-575G on 1/9/2018.
 */

@Component(modules = {AppModule.class, NetworkModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void inject(MovieShelfApp movieShelfApp);

    void inject(MovieModel movieModel);

    void inject(MovieListPresenter movieListPresenter);

    void inject(MovieListActivity movieListActivity);
}
