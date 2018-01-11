package com.bnkk.padcmovieshelf.dagger;

import com.bnkk.padcmovieshelf.network.MovieDataAgent;
import com.bnkk.padcmovieshelf.network.MovieDataAgentImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by E5-575G on 1/9/2018.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public MovieDataAgent provideMovieDataAgent(){
        return new MovieDataAgentImpl();
    }
}
