package ai.akun.moviees.feature.tvshows.data

import ai.akun.core.network.clients.MovieDbApiClient
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

class TvShowsRepositoryTest : KoinTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val apiClient: MovieDbApiClient by inject()
    private val repository: TvShowsRepository by inject()
    private val page: Int = 1
    private val genreId: Int = 10764

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<MovieDbApiClient> { mockk(relaxed = true) }
                single {
                    TvShowsRepository(get())
                }
            })
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return topRated data`() = testCoroutineDispatcher.runBlockingTest {
        val response = TestResponse.tvShowsRepository

        //GIVEN
        coEvery {
            apiClient.getTopRatedTvShows(
                page = page
            )
        }.returns((flowOf(response)))

        assertEquals(response.toTopRatedTvShows(), repository.getTopRatedTvShows(page = page).single())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return similar data`() = testCoroutineDispatcher.runBlockingTest {
        val response = TestResponse.tvShowsRepository

        //GIVEN
        coEvery {
            apiClient.getSimilarTvShows(
                genreId = genreId
            )
        }.returns((flowOf(response)))

        assertEquals(response.toTopRatedTvShows(), repository.getSimilarTvShows(genreId = genreId).single())
    }
}
