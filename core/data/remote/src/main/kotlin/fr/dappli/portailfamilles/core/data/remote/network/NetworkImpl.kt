package fr.dappli.portailfamilles.core.data.remote.network

import fr.dappli.portailfamilles.core.data.api.PersistenceKey
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkImpl(
    persistentDataSource: PersistentDataSource,
    mockEngine: HttpClientEngine? = null,
) : Network {

    private var bearerAuthProvider: BearerAuthProvider? = null

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

        install(Auth) {
            bearer {
                loadTokens {
                    val accessToken = persistentDataSource.getString(PersistenceKey.TOKEN.name)
                    accessToken?.let { BearerTokens(accessToken, null) }
                }
                refreshTokens {
                    persistentDataSource.remove(PersistenceKey.TOKEN.name)
                    null
                }
            }
            bearerAuthProvider = providers.filterIsInstance<BearerAuthProvider>().firstOrNull()
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = PROD_HOST
            }
        }
    }

    override fun clearBearerToken() {
        bearerAuthProvider?.clearToken()
    }

    private companion object {
        const val PROD_HOST = "deuillabarre-ws.portail-familles.com"
        const val DEFAULT_REQUEST_TIMEOUT_MS = 15_000L
    }
}
