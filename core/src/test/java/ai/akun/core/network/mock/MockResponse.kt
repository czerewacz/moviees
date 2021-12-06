package ai.akun.core.network.mock

import ai.akun.core.network.request.*

object MockResponse {

    val topRatedData : TopRatedData = TopRatedData(
        page = 1,
        results = listOf<Results>(),
        total_pages = 10,
        total_results = 20
    )

}