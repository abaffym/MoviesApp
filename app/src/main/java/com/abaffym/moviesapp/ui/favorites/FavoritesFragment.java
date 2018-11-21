package com.abaffym.moviesapp.ui.favorites;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abaffym.moviesapp.R;
import com.abaffym.moviesapp.model.Movie;

import java.util.List;

public class FavoritesFragment extends Fragment {

	private FavoritesViewModel viewModel;

	private FavoritesAdapter adapter;

	private CompositeDisposable compositeDisposable;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public FavoritesFragment() {
	}

	public static FavoritesFragment newInstance() {
		return new FavoritesFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_favorites, container, false);

		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			recyclerView.setLayoutManager(new LinearLayoutManager(context));
			adapter = new FavoritesAdapter();
			recyclerView.setAdapter(adapter);
		}
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Disposable disposable = viewModel.getFavoriteMovies().subscribe(new Consumer<List<Movie>>() {
			@Override
			public void accept(List<Movie> movies) {
				adapter.setMovies(movies);
			}
		});
		compositeDisposable.add(disposable);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		compositeDisposable = new CompositeDisposable();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		compositeDisposable.dispose();
	}

}
