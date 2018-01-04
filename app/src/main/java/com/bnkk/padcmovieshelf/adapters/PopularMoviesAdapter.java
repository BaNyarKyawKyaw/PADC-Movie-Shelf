package com.bnkk.padcmovieshelf.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.delegates.MovieItemDelegate;
import com.bnkk.padcmovieshelf.viewholders.PopularMoviesViewHolder;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class PopularMoviesAdapter extends BaseRecyclerAdapter<PopularMoviesViewHolder, MovieVO> {

    private MovieItemDelegate mMovieItemDelegate;

    public PopularMoviesAdapter(Context context, MovieItemDelegate movieItemDelegate) {
        super(context);
        mMovieItemDelegate = movieItemDelegate;
    }

    @Override
    public PopularMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_movie, parent, false);
        return new PopularMoviesViewHolder(view,mMovieItemDelegate);
    }
}
