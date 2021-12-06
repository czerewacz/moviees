package ai.akun.moviees.feature.tvshows.domain.usecases

import ai.akun.core.usecase.UseCaseResult
import ai.akun.moviees.feature.tvshows.domain.ITvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class GetTopRatedUseCaseTest : KoinTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val getTopRatedUseCase: GetTopRatedUseCase by inject()
    private val repository: ITvShowsRepository by inject()
    private val page: Int = 1

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { GetTopRatedUseCase(get()) }
                single<ITvShowsRepository> { mockk(relaxed = true) }
            })
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return success`() = testCoroutineDispatcher.runBlockingTest {
        val success = TopRatedTvShows()

        //GIVEN
        coEvery {
            repository.getTopRatedTvShows(
                page = page
            )
        }.returns(flowOf(success))

        //THEN
        assertEquals(
            UseCaseResult.Success(success),
            getTopRatedUseCase(
                page = page
            ).single()
        )
    }
}