package fr.dappli.portailfamilles.core.data.remote.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkImpl(
    mockEngine: HttpClientEngine? = null,
) : Network {

    private val baseJson by lazy {
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            isLenient = true
            explicitNulls = false
        }
    }

    override val client = (mockEngine?.let { HttpClient(it) } ?: HttpClient(OkHttp)).config {
        install(ContentNegotiation) {
            json(
                json = baseJson,
                contentType = ContentType.Application.Json
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = DEFAULT_REQUEST_TIMEOUT_MS
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = PROD_HOST
            }
        }
    }

    private companion object {
        const val PROD_HOST = "deuillabarre-ws.portail-familles.com"
        const val DEFAULT_REQUEST_TIMEOUT_MS = 15_000L
    }
}
