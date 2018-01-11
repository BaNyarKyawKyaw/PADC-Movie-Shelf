package com.bnkk.padcmovieshelf.delegates;

import com.bnkk.padcmovieshelf.data.vos.MovieVO;

/**
 * Created by E5-575G on 12/16/2017.
 */

public interface MovieItemDelegate {

    void onTapMovie(MovieVO movie);

    void onTapMovieOverview();
}
