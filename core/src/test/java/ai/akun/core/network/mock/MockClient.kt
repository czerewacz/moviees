package ai.akun.core.network.mock

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

val mockClient = HttpClient(MockEngine) {

    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
    engine {
        addHandler { request ->
            when (request.url.encodedPath) {
                "/3/tv/top_rated" -> {
                    respond(Json.encodeToString(MockResponse.topRatedData), HttpStatusCode.OK, responseHeaders)
                }
                "/3/tv/10764/similar" -> {
                    respond(Json.encodeToString(MockResponse.topRatedData), HttpStatusCode.OK, responseHeaders)
                }
                else -> {
                    error("Unhandled ${request.url.encodedPath}")
                }
            }
        }
    }
}
