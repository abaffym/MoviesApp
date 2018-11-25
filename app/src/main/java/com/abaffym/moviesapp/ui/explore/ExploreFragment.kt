package com.abaffym.moviesapp.ui.explore

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abaffym.moviesapp.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ExploreFragment : Fragment() {

    private var onMovieClickedListener: ExploreAdapter.OnMovieClickedListener? = null

    private lateinit var viewModel: ExploreViewModel

    private lateinit var exploreAdapter: ExploreAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var compositeDisposable : CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        // Set the exploreAdapter
        exploreAdapter = ExploreAdapter(onMovieClickedListener)

        recyclerView = view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = exploreAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allMovies.subscribeBy(
                onSuccess = { movies ->
                    exploreAdapter.setMovies(movies)
                },
                onError = {
                    Toast.makeText(context, "Could not fetch data.", Toast.LENGTH_SHORT).show()
                }).addTo(compositeDisposable)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        compositeDisposable = CompositeDisposable()
        if (context is ExploreAdapter.OnMovieClickedListener) {
            onMovieClickedListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        compositeDisposable.dispose()
        onMovieClickedListener = null
    }

    companion object {

        fun newInstance(): ExploreFragment = ExploreFragment()
    }
}
