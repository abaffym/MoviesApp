package com.abaffym.moviesapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.abaffym.moviesapp.R

private const val EXTRA_MOVIE_ID = "movie_id"

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)

        setupToolbar()

        val extras = intent.extras ?: throw IllegalStateException("Missing extras.")

        val movieId = extras.getLong(EXTRA_MOVIE_ID)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MovieDetailFragment.newInstance(movieId))
                    .commitNow()
        }
    }

    private fun setupToolbar() {
        findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {

        fun getIntent(movieId: Long, context: Context): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }
}
