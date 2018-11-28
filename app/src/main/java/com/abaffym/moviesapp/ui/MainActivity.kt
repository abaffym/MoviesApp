package com.abaffym.moviesapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.abaffym.moviesapp.R
import com.abaffym.moviesapp.extension.addOnTabSelected
import com.abaffym.moviesapp.model.Movie
import com.abaffym.moviesapp.ui.detail.MovieDetailActivity
import com.abaffym.moviesapp.ui.explore.ExploreAdapter
import com.abaffym.moviesapp.ui.explore.ExploreFragment
import com.abaffym.moviesapp.ui.favorites.FavoritesFragment
import kotlinx.android.synthetic.main.activity_main.*

private const val TAB_POSITION_EXPLORE = 0
private const val TAB_POSITION_FAVORITE = 1

private const val FRAGMENT_TAG_EXPLORE = "item_fragment_explore"
private const val FRAGMENT_TAG_FAVORITES = "item_fragment_favorites"

class MainActivity : AppCompatActivity(), ExploreAdapter.OnMovieClickedListener {

    private lateinit var exploreFragment: ExploreFragment
    private lateinit var favoritesFragment: FavoritesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupTabBar()

        exploreFragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_EXPLORE) as ExploreFragment?
                ?: ExploreFragment.newInstance()

        favoritesFragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_FAVORITES) as FavoritesFragment?
                ?: FavoritesFragment.newInstance()

        if (savedInstanceState == null) {
            navigateToTab(TAB_POSITION_EXPLORE)
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupTabBar() {
        tab_layout.addOnTabSelected(::navigateToTab)
    }

    override fun onMovieClicked(item: Movie) {
        startActivity(MovieDetailActivity.getIntent(item.id, this))
    }

    private fun navigateToTab(position: Int) = when (position) {
        TAB_POSITION_EXPLORE -> {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, exploreFragment, FRAGMENT_TAG_EXPLORE)
                    .commit()
        }
        TAB_POSITION_FAVORITE -> {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, favoritesFragment, FRAGMENT_TAG_FAVORITES)
                    .commit()
        }
        else -> {
            throw IllegalArgumentException("Invalid tab position: $position")
        }

    }
}
