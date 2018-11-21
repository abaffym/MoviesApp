package com.abaffym.moviesapp.ui.favorites;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abaffym.moviesapp.R;
import com.abaffym.moviesapp.model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

	private final List<Movie> movies = new ArrayList<>();

	public FavoritesAdapter() {
	}

	public void setMovies(List<Movie> items) {
		movies.clear();
		movies.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_fragment_favorites, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		final Movie movie = movies.get(position);

		holder.movieTitle.setText(String.valueOf(movies.get(position).getTitle()));

		Glide.with(holder.view)
				.load(movie.getPosterPath())
				.into(holder.moviePoster);
	}

	@Override
	public int getItemCount() {
		return movies.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public final View view;
		public final TextView movieTitle;
		public final ImageView moviePoster;

		public ViewHolder(View view) {
			super(view);
			this.view = view;
			movieTitle = view.findViewById(R.id.item_movie_title);
			moviePoster = view.findViewById(R.id.item_movie_poster);
		}

	}
}
