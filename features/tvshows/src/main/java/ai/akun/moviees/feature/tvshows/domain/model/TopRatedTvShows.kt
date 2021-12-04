package ai.akun.moviees.feature.tvshows.domain.model

import ai.akun.core.network.request.Results

class TopRatedTvShows(
    val page: Int,
    val results: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)