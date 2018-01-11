package com.bnkk.padcmovieshelf.dagger;

import android.content.Context;

import com.bnkk.padcmovieshelf.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by E5-575G on 1/9/2018.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public ConfigUtils provideConfigUtils(Context context) {
        return new ConfigUtils(context);
    }
}
