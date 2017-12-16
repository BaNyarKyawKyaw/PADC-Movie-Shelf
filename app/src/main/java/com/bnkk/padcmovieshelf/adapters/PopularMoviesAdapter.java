package com.bnkk.padcmovieshelf.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.viewholders.PopularMoviesViewHolder;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class PopularMoviesAdapter extends BaseRecyclerAdapter<PopularMoviesViewHolder, MovieVO> {

    public PopularMoviesAdapter(Context context) {
        super(context);
    }

    @Override
    public PopularMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_movie, parent, false);
        return new PopularMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularMoviesViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }
}
