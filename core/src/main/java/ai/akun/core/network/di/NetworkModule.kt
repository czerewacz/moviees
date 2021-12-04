package ai.akun.core.network.di

import ai.akun.core.network.Endpoints.BASE_URL
import ai.akun.core.network.Endpoints.SIMILARS
import ai.akun.core.network.Endpoints.TOP_RATED
import ai.akun.core.network.clients.MovieDbApiClient
import ai.akun.core.network.clients.ProdHttpClient
import org.koin.dsl.module

val networkModule = module {
    single {
        MovieDbApiClient(
            httpClient = ProdHttpClient().httpClient,
            baseUrl = BASE_URL,
            backgroundDispatcher = get(),
            apiKey = "e94548a8cfdd2a558f15ac140df64f9d", //TODO Migrate to API KEY NDK
            topRatedEndpoint = TOP_RATED,
            similarTvEndpoint = SIMILARS
        )
    }
}
