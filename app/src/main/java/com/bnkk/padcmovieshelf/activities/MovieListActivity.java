package com.bnkk.padcmovieshelf.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.bnkk.padcmovieshelf.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends BaseActivity {

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
        mPopularMoviesAdapter = new PopularMoviesAdapter(getApplicationContext());
        srvPopularMovie.setAdapter(mPopularMoviesAdapter);

        SmartScrollListener mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReached() {
                MovieModel.getObjInstance().loadMoreMovies();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MovieModel.getObjInstance().forceRefreshNews();
            }
        });

        srvPopularMovie.addOnScrollListener(mSmartScrollListener);
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
        mPopularMoviesAdapter.appendNewData(event.getLoadedMovies());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(srvPopularMovie, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}
