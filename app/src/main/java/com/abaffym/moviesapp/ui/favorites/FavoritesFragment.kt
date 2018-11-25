package com.abaffym.moviesapp.ui.favorites

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abaffym.moviesapp.R
import io.reactivex.disposables.CompositeDisposable

class FavoritesFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var favoritesAdapter: FavoritesAdapter

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        view as? RecyclerView ?: throw IllegalStateException("Invalid root view")

        favoritesAdapter = FavoritesAdapter()
        view.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = favoritesAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val disposable = viewModel.favoriteMovies.subscribe { movies -> favoritesAdapter.setMovies(movies) }
        compositeDisposable.add(disposable)
    }


    override fun onDetach() {
        super.onDetach()
        compositeDisposable.dispose()
    }

    companion object {

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

}
