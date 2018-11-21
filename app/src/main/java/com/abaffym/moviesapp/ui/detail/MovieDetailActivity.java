package com.abaffym.moviesapp.ui.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.abaffym.moviesapp.R;

public class MovieDetailActivity extends AppCompatActivity {

	private static final String EXTRA_MOVIE_ID = "movie_id";

	public static Intent getIntent(long movieId, Context context) {
		Intent intent = new Intent(context, MovieDetailActivity.class);
		intent.putExtra(EXTRA_MOVIE_ID, movieId);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_detail_activity);

		setupToolbar();

		Bundle extras = getIntent().getExtras();

		if (extras == null) {
			throw new IllegalStateException("Missing extras.");
		}

		long movieId = extras.getLong(EXTRA_MOVIE_ID);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, MovieDetailFragment.newInstance(movieId))
					.commitNow();
		}
	}

	public void setupToolbar() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		if (toolbar != null) {
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
}
