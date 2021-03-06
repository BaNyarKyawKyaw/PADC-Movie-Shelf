package com.bnkk.padcmovieshelf.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.delegates.MovieItemDelegate;
import com.bnkk.padcmovieshelf.utils.AppConstants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class PopularMoviesViewHolder extends BaseViewHolder<MovieVO> {

    @BindView(R.id.iv_movie_cover)
    ImageView ivMovieCover;

    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;

    @BindView(R.id.tv_movie_type)
    TextView tvMovieType;

    @BindView(R.id.tv_rating_average)
    TextView tvRatingAverage;

    private MovieItemDelegate mMovieItemDelegate;

    public PopularMoviesViewHolder(View itemView, MovieItemDelegate movieItemDelegate) {
        super(itemView);
        mMovieItemDelegate = movieItemDelegate;
    }

    @Override
    public void setData(MovieVO data) {
        mData = data;

        if (data != null) {

            if (data.getTitle() != null) {
                tvMovieName.setVisibility(View.VISIBLE);
                tvMovieName.setText(data.getTitle());
            } else {
                tvMovieName.setVisibility(View.INVISIBLE);
            }

            tvRatingAverage.setText(String.valueOf(data.getVoteAverage()));

            if (data.getPosterPath() != null) {
                ivMovieCover.setVisibility(View.VISIBLE);
                Glide
                        .with(ivMovieCover.getContext())
                        .load(AppConstants.IMAGE_BASE_PATH + "original" + data.getPosterPath())
                        .into(ivMovieCover);
            } else {
                ivMovieCover.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        mMovieItemDelegate.onTapMovie(mData);
    }
}
