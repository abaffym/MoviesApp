package com.abaffym.moviesapp.data.remote.entity;

import com.squareup.moshi.Json;

import java.util.List;

public class DiscoverMoviesResponse<T> {

	@Json(name = "page")
	private int page;

	@Json(name = "results")
	private List<T> results;

	@Json(name = "total_pages")
	private int totalPages;

	@Json(name = "total_results")
	private long totalResults;

	public DiscoverMoviesResponse(int page, List<T> results, int totalPages, long totalResults) {
		this.page = page;
		this.results = results;
		this.totalPages = totalPages;
		this.totalResults = totalResults;
	}

	public int getPage() {
		return page;
	}

	public List<T> getResults() {
		return results;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public long getTotalResults() {
		return totalResults;
	}

}
