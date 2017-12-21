package com.bnkk.padcmovieshelf.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bnkk.padcmovieshelf.R;
import com.bnkk.padcmovieshelf.adapters.PopularMoviesAdapter;
import com.bnkk.padcmovieshelf.components.EmptyViewPod;
import com.bnkk.padcmovieshelf.components.SmartRecyclerView;
import com.bnkk.padcmovieshelf.components.SmartScrollListener;
import com.bnkk.padcmovieshelf.data.models.MovieModel;
import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.bnkk.padcmovieshelf.delegates.MovieItemDelegate;
import com.bnkk.padcmovieshelf.events.RestApiEvents;
import com.bnkk.padcmovieshelf.persistence.MovieContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends BaseActivity
        implements MovieItemDelegate, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int NEWS_LIST_LOADER_ID = 100;

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @BindView(R.id.fab_share)
    FloatingActionButton fab;

    @BindView(R.id.rv_popular_movie_list)
    SmartRecyclerView srvPopularMovie;

    @BindView(R.id.vp_empty_movies)
    EmptyViewPod vpEmptyMovies;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private PopularMoviesAdapter mPopularMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

            actionBar.setTitle("Movie Shelf");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        srvPopularMovie.setEmptyView(vpEmptyMovies);
        srvPopularMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        mPopularMoviesAdapter = new PopularMoviesAdapter(getApplicationContext(), this);
        srvPopularMovie.setAdapter(mPopularMoviesAdapter);

        SmartScrollListener mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReached() {
                MovieModel.getObjInstance().loadMoreMovies(getApplicationContext());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MovieModel.getObjInstance().forceRefreshNews(getApplicationContext());
            }
        });

        srvPopularMovie.addOnScrollListener(mSmartScrollListener);

        getSupportLoaderManager().initLoader(NEWS_LIST_LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        List<MovieVO> newsList = MovieModel.getObjInstance().getMovies();
        if (!newsList.isEmpty()) {
            mPopularMoviesAdapter.setNewData(newsList);
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
        /*
        mPopularMoviesAdapter.appendNewData(event.getLoadedMovies());
        swipeRefreshLayout.setRefreshing(false);
        */
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(srvPopularMovie, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onTapMovieOverview() {
        Intent intent = MovieDetailsActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<MovieVO> movieList = new ArrayList<>();

            do {
                MovieVO movie = MovieVO.parseFromCursor(data);
                movieList.add(movie);
            } while (data.moveToNext());

            mPopularMoviesAdapter.setNewData(movieList);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
