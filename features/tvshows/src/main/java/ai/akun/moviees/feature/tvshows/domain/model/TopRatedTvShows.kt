package ai.akun.moviees.feature.tvshows.domain.model

class TopRatedTvShows(
    val page: Int,
    val results: MutableList<TvShow>,
    val total_pages: Int,
    val total_results: Int
)