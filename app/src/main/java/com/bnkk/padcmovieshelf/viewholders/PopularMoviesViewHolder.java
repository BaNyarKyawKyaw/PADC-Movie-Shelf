package com.bnkk.padcmovieshelf.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.utils.AppConstants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public PopularMoviesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(MovieVO data) {

        tvMovieName.setText(data.getTitle());

        tvRatingAverage.setText(String.valueOf(data.getVoteAverage()));

        Glide
                .with(ivMovieCover.getContext())
                .load(AppConstants.IMAGE_BASE_PATH + "original" + data.getPosterPath())
                .into(ivMovieCover);
    }
}
