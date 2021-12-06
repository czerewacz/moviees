package ai.akun.core.network.clients

import ai.akun.core.network.mock.MockResponse
import ai.akun.core.network.mock.mockClient
import ai.akun.core.platform.ApiKeyRetriever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class MovieDbApiClientTest : KoinTest {

    val apiClient: MovieDbApiClient by inject()
    private val page: Int = 1
    private val apiKey: String = "e94548a8cfdd2a558f15ac140df64f9d"

    @ExperimentalCoroutinesApi
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single {
                    MovieDbApiClient(
                        httpClient = mockClient,
                        apiKey = apiKey,
                        backgroundDispatcher = TestCoroutineDispatcher(),
                        baseUrl = "https;//api.themoviedb.org",
                        topRatedEndpoint = "/3/tv/top_rated",
                        similarTvEndpoint = "/3/tv/%s/similar"
                    )
                }
            })
    }

    @Test
    fun `get topRated data and return 200`() = runBlocking {
        apiClient.getTopRatedTvShows(page = page).collect { response ->
            assertEquals(MockResponse.topRatedData, response)
        }
    }

    @Test
    fun `get similar data and return 200`() = runBlocking {
        apiClient.getSimilarTvShows(genreId = 10764).collect { response ->
            assertEquals(MockResponse.topRatedData, response)
        }
    }

}