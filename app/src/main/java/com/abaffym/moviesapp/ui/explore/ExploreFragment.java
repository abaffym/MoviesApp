package com.abaffym.moviesapp.ui.explore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abaffym.moviesapp.R;
import com.abaffym.moviesapp.data.local.MoviesLocalDataSource;
import com.abaffym.moviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

	private ExploreAdapter.OnMovieClickedListener onMovieClickedListener;

	private ExploreViewModel viewModel;

	private ExploreAdapter adapter;

	private CompositeDisposable compositeDisposable;

	private RecyclerView recyclerView;

	private ProgressBar progressBar;

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
		Context context = view.getContext();
		recyclerView = view.findViewById(R.id.list);
		recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
		adapter = new ExploreAdapter(onMovieClickedListener);
		recyclerView.setAdapter(adapter);

		progressBar = view.findViewById(R.id.progress);
		progressBar.setVisibility(View.VISIBLE);
		recyclerView.setVisibility(View.GONE);
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Disposable disposable = viewModel.getAllMovies().subscribe(new Consumer<List<Movie>>() {
			@Override
			public void accept(List<Movie> movies) {
				progressBar.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);
				adapter.setMovies(movies);
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

		MoviesLocalDataSource.setCachedMovies(new ArrayList<Movie>());
	}
}
