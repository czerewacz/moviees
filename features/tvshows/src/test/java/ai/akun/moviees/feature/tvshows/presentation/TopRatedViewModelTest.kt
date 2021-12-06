package ai.akun.moviees.feature.tvshows.presentation

import ai.akun.core.usecase.UseCaseResult
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.usecases.GetTopRatedUseCase
import ai.akun.weatherme.rules.MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertIs

class TopRatedViewModelTest : KoinTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val viewModel: TopRatedViewModel by inject()
    private val getTopRatedUseCase: GetTopRatedUseCase by inject()
    private val page : Int = 1

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<GetTopRatedUseCase> { mockk(relaxed = true) }
                viewModel { TopRatedViewModel(get()) }
            })
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `state should change to success`() = testCoroutineDispatcher.runBlockingTest {
        //GIVEN
        val success = TopRatedTvShows()

        coEvery {
            getTopRatedUseCase(page = page)
        }.returns(flowOf(UseCaseResult.Success(success)))
        viewModel.uiState.observeForever { }

        //WHEN
        viewModel.getTopRatedTvShows()

        //THEN
        assertIs<TopRatedViewModel.UIState.Success>(viewModel.uiState.value)
    }

    @Test
    fun `state should change to failure`() = testCoroutineDispatcher.runBlockingTest {
        //GIVEN
        val error = Throwable()

        coEvery {
            getTopRatedUseCase(page = page)
        }.returns(flowOf(UseCaseResult.Error(error)))
        viewModel.uiState.observeForever { }

        //WHEN
        viewModel.getTopRatedTvShows()

        //THEN
        assertIs<TopRatedViewModel.UIState.Error>(viewModel.uiState.value)
    }
}