package com.abaffym.moviesapp.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.abaffym.moviesapp.R
import com.abaffym.moviesapp.extension.toSimpleString
import com.bumptech.glide.Glide

private const val ARG_MOVIE_ID = "movie_id"

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        val args = arguments ?: throw IllegalStateException("Missing movie id")

        val movieId = args.getLong(ARG_MOVIE_ID)
        viewModel.setMovieId(movieId)

        renderDetail()
        setupFab()
    }

    private fun setupFab() {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.detail_fab)
        setFabIcon()
        fab.setOnClickListener {
            if (!viewModel.isFavorite) {
                viewModel.addToFavorites()
                Toast.makeText(requireContext(), R.string.message_added_to_favorites, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.removeFromFavorites()
                Toast.makeText(requireContext(), R.string.message_removed_from_favorites, Toast.LENGTH_SHORT).show()
            }
            setFabIcon()
        }
    }

    private fun renderDetail() {
        with(viewModel.movieDetail) {
            val titleTextView = requireActivity().findViewById<TextView>(R.id.detail_movie_title)
            titleTextView.text = title

            val releaseDateTextView = requireActivity().findViewById<TextView>(R.id.detail_movie_release_date)
            releaseDateTextView.text = getString(R.string.movie_detail_format_release_date, releaseDate.toSimpleString())

            val ratingTextView = requireActivity().findViewById<TextView>(R.id.detail_movie_rating)
            ratingTextView.text = averageVote.toString()

            val posterImageView = requireActivity().findViewById<ImageView>(R.id.detail_movie_poster)
            Glide.with(this@MovieDetailFragment)
                    .load(posterPath)
                    .into(posterImageView)
        }


    }

    private fun setFabIcon() {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.detail_fab)
        if (!viewModel.isFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_like_white_24dp))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_dislike_white_24dp))
        }
    }

    companion object {
        fun newInstance(movieId: Long): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putLong(ARG_MOVIE_ID, movieId)
            fragment.arguments = bundle
            return fragment
        }
    }
}
