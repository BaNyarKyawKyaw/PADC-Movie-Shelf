package com.bnkk.padcmovieshelf.network;

import android.content.Context;

/**
 * Created by E5-575G on 12/12/2017.
 */

public interface MovieDataAgent {

    void loadMovies(String accessToken, int page, Context context);
}
