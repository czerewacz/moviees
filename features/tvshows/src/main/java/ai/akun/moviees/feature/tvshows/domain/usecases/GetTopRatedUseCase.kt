package ai.akun.moviees.feature.tvshows.domain.usecases

import ai.akun.moviees.feature.tvshows.domain.ITvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GetTopRatedUseCase(
    private val tvShowsRepository: ITvShowsRepository
) {
    suspend operator fun invoke(): Flow<PagingData<TvShow>> {
        return tvShowsRepository.getTopRatedTvShows()
    }
}