package com.abaffym.moviesapp.ui.explore;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.abaffym.moviesapp.R;
import com.abaffym.moviesapp.model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

	public interface OnMovieClickedListener {
		void onMovieClicked(Movie item);
	}

	private final List<Movie> movies = new ArrayList<>();
	private final OnMovieClickedListener onMovieClickedListener;

	public ExploreAdapter(OnMovieClickedListener listener) {
		onMovieClickedListener = listener;
	}

	public void setMovies(List<Movie> items) {
		movies.clear();
		movies.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_fragment_explore, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ExploreAdapter.ViewHolder holder, int position) {
		final Movie movie = movies.get(position);

		Glide.with(holder.view)
				.load(movie.getPosterPath())
				.into(holder.moviePoster);

		holder.view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onMovieClickedListener != null) {
					onMovieClickedListener.onMovieClicked(movie);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return movies.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public final View view;
		public final ImageView moviePoster;

		public ViewHolder(View view) {
			super(view);
			this.view = view;
			moviePoster = view.findViewById(R.id.item_movie_poster);
		}

	}
}
