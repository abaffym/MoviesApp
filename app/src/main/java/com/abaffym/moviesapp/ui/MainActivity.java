package com.abaffym.moviesapp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.abaffym.moviesapp.R;
import com.abaffym.moviesapp.model.Movie;
import com.abaffym.moviesapp.ui.detail.MovieDetailActivity;
import com.abaffym.moviesapp.ui.explore.ExploreAdapter;
import com.abaffym.moviesapp.ui.explore.ExploreFragment;
import com.abaffym.moviesapp.ui.favorites.FavoritesFragment;

public class MainActivity extends AppCompatActivity implements ExploreAdapter.OnMovieClickedListener {

    private static final int TAB_POSITION_EXPLORE = 0;
    private static final int TAB_POSITION_FAVORITE = 1;

    private static final String FRAGMENT_TAG_EXPLORE = "item_fragment_explore";
    private static final String FRAGMENT_TAG_FAVORITES = "item_fragment_favorites";

    private ExploreFragment exploreFragment;
    private FavoritesFragment favoritesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupTabBar();

        exploreFragment = (ExploreFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_EXPLORE);

        if (exploreFragment == null) {
            exploreFragment = ExploreFragment.newInstance();
            navigateToTab(TAB_POSITION_EXPLORE);
        }

        favoritesFragment = (FavoritesFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_FAVORITES);

        if (favoritesFragment == null) {
            favoritesFragment = FavoritesFragment.newInstance();
        }

    }

    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setupTabBar() {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                navigateToTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onMovieClicked(Movie item) {
        startActivity(MovieDetailActivity.getIntent(item.getId(), this));
    }

    private void navigateToTab(int position) {
        switch (position) {
            case TAB_POSITION_EXPLORE: {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, exploreFragment, FRAGMENT_TAG_EXPLORE)
                        .commit();
                break;
            }
            case TAB_POSITION_FAVORITE: {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, favoritesFragment, FRAGMENT_TAG_FAVORITES)
                        .commit();
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid tab position: " + position);
            }
        }

    }
}
