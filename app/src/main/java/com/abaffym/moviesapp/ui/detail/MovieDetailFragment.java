package com.abaffym.moviesapp.ui.detail;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abaffym.moviesapp.R;
import com.abaffym.moviesapp.model.Movie;
import com.abaffym.moviesapp.utils.DateUtils;
import com.bumptech.glide.Glide;

public class MovieDetailFragment extends Fragment {

    private static final String ARG_MOVIE_ID = "movie_id";

    private MovieDetailViewModel viewModel;

    public static MovieDetailFragment newInstance(long movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_MOVIE_ID, movieId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        if (getArguments() == null) {
            throw new IllegalStateException("Missing movie id");
        }

        long movieId = getArguments().getLong(ARG_MOVIE_ID);
        viewModel.setMovieId(movieId);

        renderDetail();
        setupFab();

    }

    private void setupFab() {
        FloatingActionButton fab = requireActivity().findViewById(R.id.detail_fab);
        setFabIcon();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.isFavorite()) {
                    viewModel.addToFavorites();
                    Toast.makeText(requireContext(), R.string.message_added_to_favorites, Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.removeFromFavorites();
                    Toast.makeText(requireContext(), R.string.message_removed_from_favorites, Toast.LENGTH_SHORT).show();
                }
                setFabIcon();
            }
        });
    }

    private void renderDetail() {
        Movie movie = viewModel.getMovieDetail();

        TextView titleTextView = requireActivity().findViewById(R.id.detail_movie_title);
        titleTextView.setText(movie.getTitle());

        TextView releaseDateTextView = requireActivity().findViewById(R.id.detail_movie_release_date);
        String releaseDate = getString(R.string.movie_detail_format_release_date,
                DateUtils.toSimpleString(movie.getReleaseDate()));
        releaseDateTextView.setText(releaseDate);

        TextView ratingTextView = requireActivity().findViewById(R.id.detail_movie_rating);
        ratingTextView.setText(String.valueOf(movie.getAverageVote()));

        ImageView posterImageView = requireActivity().findViewById(R.id.detail_movie_poster);
        Glide.with(this)
                .load(movie.getPosterPath())
                .into(posterImageView);
    }

    private void setFabIcon() {
        FloatingActionButton fab = requireActivity().findViewById(R.id.detail_fab);
        if (viewModel.isFavorite()) {
            fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_like_white_24dp));
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_dislike_white_24dp));
        }
    }
}
