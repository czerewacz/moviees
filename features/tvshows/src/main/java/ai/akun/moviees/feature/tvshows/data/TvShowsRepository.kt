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
    override suspend fun getTopRatedTvShows(): Flow<TopRatedTvShows> {
        return apiClient.getTopRatedTvShows().map { response ->
            response.toTopRatedTvShows()
        }
    }
}