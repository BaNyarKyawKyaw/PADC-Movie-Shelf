package com.bnkk.padcmovieshelf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.viewholders.MovieReviewViewHolder;

/**
 * Created by E5-575G on 12/16/2017.
 */

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewViewHolder> {

    private LayoutInflater mLayoutInflater;

    public MovieReviewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_movie_review, parent, false);
        return new MovieReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
