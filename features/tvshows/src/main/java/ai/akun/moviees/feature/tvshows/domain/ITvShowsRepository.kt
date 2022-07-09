package ai.akun.moviees.feature.tvshows.domain

import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ITvShowsRepository {

    suspend fun getTopRatedTvShows(): Flow<PagingData<TvShow>>

    suspend fun getSimilarTvShows(genreId: Int): Flow<TopRatedTvShows>

}