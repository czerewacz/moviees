package ai.akun.core.network.clients

import ai.akun.core.network.error.NetworkFailure
import ai.akun.core.network.request.TopRatedData
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class MovieDbApiClient(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val apiKey: String,
    private val backgroundDispatcher: CoroutineContext,
    private val topRatedEndpoint: String,
    private val similarTvEndpoint: String
) {
    fun getTopRatedTvShows(): Flow<TopRatedData> {
        return flow<TopRatedData> {
            emit(httpClient.get(
                scheme = "https",
                host = baseUrl,
                port = 443,
                path = topRatedEndpoint
            ) {
                parameter("api_key", apiKey)
            })
        }.flowOn(
            backgroundDispatcher
        ).retry(retries = 3) { error ->
            if (error is NetworkFailure.Redirect) {
                val seconds = (2000).toLong()
                delay(seconds)
                return@retry true
            } else {
                return@retry false
            }
        }
    }

    fun getSimilarTvShows(
        genreId: Int
    ): Flow<TopRatedData> {
        return flow<TopRatedData> {
            emit(httpClient.get(
                scheme = "https",
                host = baseUrl,
                port = 443,
                path = String.format(similarTvEndpoint, genreId)
            ) {
                parameter("api_key", apiKey)
            })
        }.flowOn(
            backgroundDispatcher
        ).retry(retries = 3) { error ->
            if (error is NetworkFailure.Redirect) {
                val seconds = (2000).toLong()
                delay(seconds)
                return@retry true
            } else {
                return@retry false
            }
        }
    }
}