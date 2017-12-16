package com.bnkk.padcmovieshelf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.viewholders.MovieTrailerViewHolder;

import java.util.List;


/**
 * Created by E5-575G on 12/15/2017.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerViewHolder> {

    private LayoutInflater mLayoutInflater;

    public MovieTrailerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_movie_trailer, parent, false);
        return new MovieTrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
