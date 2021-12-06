package ai.akun.moviees.feature.tvshows.data

import ai.akun.core.network.request.Results
import ai.akun.core.network.request.TopRatedData

object TestResponse {

    val tvShowsRepository : TopRatedData = TopRatedData(
        page = 1,
        results = listOf<Results>(),
        total_pages = 10,
        total_results = 20
    )

}