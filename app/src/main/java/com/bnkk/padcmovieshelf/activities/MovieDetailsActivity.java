package com.bnkk.padcmovieshelf.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.adapters.MovieTrailerAdapter;
import com.bnkk.padcmovieshelf.components.SmartRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieDetailsActivity extends BaseActivity {

    @BindView(R.id.rv_movie_trailer_list)
    SmartRecyclerView rvMovieTrailer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this, this);

        rvMovieTrailer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        MovieTrailerAdapter mMovieTrailerAdapter = new MovieTrailerAdapter(getApplicationContext());
        rvMovieTrailer.setAdapter(mMovieTrailerAdapter);
    }
}
