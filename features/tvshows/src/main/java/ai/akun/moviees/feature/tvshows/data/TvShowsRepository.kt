package ai.akun.moviees.feature.tvshows.data

import ai.akun.core.network.clients.MovieDbApiClient
import ai.akun.moviees.feature.tvshows.domain.ITvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvShowsRepository(
    private val apiClient: MovieDbApiClient
) : ITvShowsRepository {
    override suspend fun getTopRatedTvShows(page: Int): Flow<TopRatedTvShows> {
        return apiClient.getTopRatedTvShows(page = page).map { response ->
            response.toTopRatedTvShows()
        }
    }

    override suspend fun getSimilarTvShows(genreId: Int): Flow<TopRatedTvShows> {
        return apiClient.getSimilarTvShows(genreId = genreId).map { response ->
            response.toTopRatedTvShows()
        }
    }
}