package ai.akun.moviees.feature.tvshows.domain

import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowsRepository {

    suspend fun getTopRatedTvShows(): Flow<TopRatedTvShows>

    suspend fun getSimilarTvShows(genreId: Int): Flow<TopRatedTvShows>

}