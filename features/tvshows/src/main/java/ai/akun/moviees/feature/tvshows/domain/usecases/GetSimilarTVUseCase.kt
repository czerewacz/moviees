package ai.akun.moviees.feature.tvshows.domain.usecases

import ai.akun.core.usecase.UseCaseResult
import ai.akun.moviees.feature.tvshows.domain.ITvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetSimilarTVUseCase(
    private val tvShowsRepository: ITvShowsRepository
) {
    suspend operator fun invoke(
        genreId: Int
    ): Flow<UseCaseResult<TopRatedTvShows>> {
        return tvShowsRepository.getSimilarTvShows(genreId = genreId).map { topRatedTvShows ->
            UseCaseResult.Success(topRatedTvShows) as UseCaseResult<TopRatedTvShows>
        }.catch { error ->
            emit(UseCaseResult.Error(exception = error))
        }
    }
}