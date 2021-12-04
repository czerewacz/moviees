package ai.akun.core.network.clients

import io.ktor.client.*
import kotlin.coroutines.CoroutineContext

class MovieDbApiClient(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val apiKey: String,
    private val backgroundDispatcher: CoroutineContext,
    private val topRatedEndpoint: String,
    private val similarTvEndpoint: String
) {
    fun getTopRatedTvShows(){

    }

    fun getSimilarTvShows(){

    }
}