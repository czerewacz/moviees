package ai.akun.moviees.feature.tvshows.data

import ai.akun.core.network.clients.MovieDbApiClient
import ai.akun.moviees.feature.tvshows.domain.ITvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvShowsRepository(
    private val apiClient: MovieDbApiClient
) : ITvShowsRepository {
    override suspend fun getTopRatedTvShows(): Flow<PagingData<TvShow>> = Pager(
        PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            maxSize = 180,
            enablePlaceholders = false
        ),
        //Network call handled by ChargesPagingSource Class
        pagingSourceFactory = {
            TvShowsPagingSource(
                apiClient = apiClient
            )
        }
    ).flow

    override suspend fun getSimilarTvShows(genreId: Int): Flow<TopRatedTvShows> {
        return apiClient.getSimilarTvShows(genreId = genreId).map { response ->
            response.toTopRatedTvShows()
        }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 60
    }
}