package com.bnkk.padcmovieshelf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.viewholders.MovieGenreViewHolder;

/**
 * Created by E5-575G on 12/16/2017.
 */

public class MovieGenreAdapter extends RecyclerView.Adapter<MovieGenreViewHolder> {

    private LayoutInflater mLayoutInflater;

    public MovieGenreAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieGenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_movie_genre, parent, false);
        return new MovieGenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieGenreViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
