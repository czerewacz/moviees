package ai.akun.core.network.di

import ai.akun.core.network.Endpoints.BASE_URL
import ai.akun.core.network.Endpoints.SIMILARS
import ai.akun.core.network.Endpoints.TOP_RATED
import ai.akun.core.network.clients.MovieDbApiClient
import ai.akun.core.network.clients.ProdHttpClient
import ai.akun.core.platform.ApiKeyRetriever
import org.koin.dsl.module

val networkModule = module {
    single {
        MovieDbApiClient(
            httpClient = ProdHttpClient().httpClient,
            baseUrl = BASE_URL,
            backgroundDispatcher = get(),
            apiKey = ApiKeyRetriever.getMovieApiKey(),
            topRatedEndpoint = TOP_RATED,
            similarTvEndpoint = SIMILARS
        )
    }
}
