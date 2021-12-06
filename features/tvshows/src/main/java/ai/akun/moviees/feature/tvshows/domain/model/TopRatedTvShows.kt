package ai.akun.moviees.feature.tvshows.domain.model

data class TopRatedTvShows(
    val page: Int = 0,
    val results: List<TvShow> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)