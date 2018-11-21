package com.abaffym.moviesapp.ui.explore;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abaffym.moviesapp.R;
import com.abaffym.moviesapp.model.Movie;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ExploreFragment extends Fragment {

	private ExploreAdapter.OnMovieClickedListener onMovieClickedListener;

	private ExploreViewModel viewModel;

	private ExploreAdapter adapter;

	private CompositeDisposable compositeDisposable;

	private RecyclerView recyclerView;

	public ExploreFragment() {
	}

	public static ExploreFragment newInstance() {
		return new ExploreFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewModel = ViewModelProviders.of(this).get(ExploreViewModel.class);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_explore, container, false);

		// Set the adapter
		recyclerView = view.findViewById(R.id.list);
		recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
		adapter = new ExploreAdapter(onMovieClickedListener);
		recyclerView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Disposable disposable = viewModel.getAllMovies().subscribe(new Consumer<List<Movie>>() {
			@Override
			public void accept(List<Movie> movies) {
				adapter.setMovies(movies);
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) {
				Toast.makeText(getContext(),"Could not fetch data.", Toast.LENGTH_SHORT).show();
			}
		});
		compositeDisposable.add(disposable);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		compositeDisposable = new CompositeDisposable();
		if (context instanceof ExploreAdapter.OnMovieClickedListener) {
			onMovieClickedListener = (ExploreAdapter.OnMovieClickedListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnListFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		compositeDisposable.dispose();
		onMovieClickedListener = null;
	}
}
