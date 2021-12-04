package ai.akun.core.network.clients

import ai.akun.core.network.error.NetworkFailure
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class ProdHttpClient {
    val httpClient = HttpClient(CIO) {

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        expectSuccess = false

        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                when (statusCode) {
                    in 300..399 -> throw NetworkFailure.Redirect("Gateway error")
                    in 400..499 -> throw NetworkFailure.ClientError(
                        response.status.value,
                        response.receive<ErrorResponse>().message
                    )
                    in 500..599 -> throw NetworkFailure.ServerError(
                        response.status.value,
                        response.receive<ErrorResponse>().message
                    )
                }

                if (statusCode >= 600) {
                    throw NetworkFailure.UnknownError("Unknown Network error")
                }
            }
        }
    }
}