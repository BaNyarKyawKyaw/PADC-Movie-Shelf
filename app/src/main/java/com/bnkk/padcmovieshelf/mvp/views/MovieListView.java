package com.bnkk.padcmovieshelf.mvp.views;

import android.content.Context;

import com.bnkk.padcmovieshelf.data.vos.MovieVO;

import java.util.List;

/**
 * Created by E5-575G on 1/9/2018.
 */

public interface MovieListView {

    void displayMovieList(List<MovieVO> movieList);

    void showLoading();

    void navigateToMovieDetails(MovieVO movies);

    Context getContext();
}
