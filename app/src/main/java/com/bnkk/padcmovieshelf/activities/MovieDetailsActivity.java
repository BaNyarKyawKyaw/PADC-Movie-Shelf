package com.bnkk.padcmovieshelf.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.adapters.MovieGenreAdapter;
import com.bnkk.padcmovieshelf.adapters.MovieReviewAdapter;
import com.bnkk.padcmovieshelf.adapters.MovieTrailerAdapter;
import com.bnkk.padcmovieshelf.components.SmartRecyclerView;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.persistence.MovieContract;
import com.bnkk.padcmovieshelf.utils.AppConstants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieDetailsActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String MOVIE_ID = "MOVIE_ID";
    private static final int MOVIE_DETAILS_LOADER_ID = 101;

    @BindView(R.id.rv_movie_trailer_list)
    SmartRecyclerView rvMovieTrailer;

    @BindView(R.id.rv_movie_genre)
    SmartRecyclerView rvMovieGenre;

    @BindView(R.id.rv_movie_review)
    SmartRecyclerView rvMovieReview;

    @BindView(R.id.iv_movie_cover)
    ImageView ivMovieCover;

    @BindView(R.id.iv_back_drop)
    ImageView ivBackDrop;

    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;

    @BindView(R.id.tv_rating_average)
    TextView tvRatingAverage;

    @BindView(R.id.tv_released_date)
    TextView tvReleasedDate;

    @BindView(R.id.tv_movie_overview)
    TextView tvMovieOverview;

    private String mMovieId;

    public static Intent newIntent(Context context, String movieId) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this, this);

        rvMovieTrailer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        MovieTrailerAdapter mMovieTrailerAdapter = new MovieTrailerAdapter(getApplicationContext());
        rvMovieTrailer.setAdapter(mMovieTrailerAdapter);

        rvMovieGenre.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        MovieGenreAdapter mMovieGenreAdapter = new MovieGenreAdapter(getApplicationContext());
        rvMovieGenre.setAdapter(mMovieGenreAdapter);

        rvMovieReview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        MovieReviewAdapter mMovieReviewAdapter = new MovieReviewAdapter(getApplicationContext());
        rvMovieReview.setAdapter(mMovieReviewAdapter);

        mMovieId = getIntent().getStringExtra(MOVIE_ID);
        if (TextUtils.isEmpty(mMovieId)) {
            throw new UnsupportedOperationException("Movie Id required for MovieDetailsActivity");
        } else {
            getSupportLoaderManager().initLoader(MOVIE_DETAILS_LOADER_ID, null, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?", new String[]{mMovieId},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            MovieVO movies = MovieVO.parseFromCursor(getApplicationContext(), data);
            bindData(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void bindData(MovieVO movie) {
        Glide
                .with(ivMovieCover.getContext())
                .load(AppConstants.IMAGE_BASE_PATH + "original" + movie.getPosterPath())
                .into(ivMovieCover);

        Glide
                .with(ivBackDrop.getContext())
                .load(AppConstants.IMAGE_BASE_PATH + "original" + movie.getBackdropPath())
                .into(ivBackDrop);

        tvMovieName.setText(movie.getTitle());
        tvRatingAverage.setText(String.valueOf(movie.getVoteAverage()));
        tvReleasedDate.setText("Released : " + movie.getReleaseDate());
        tvMovieOverview.setText(movie.getOverView());
    }
}
