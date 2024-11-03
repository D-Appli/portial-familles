package fr.dappli.portailfamilles.server

import fr.dappli.portailfamilles.server.routing.Restaurants
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.resources.Resources
import io.ktor.server.resources.get
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerImpl @Inject constructor() : Server {

    private val baseJson by lazy {
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            isLenient = true
        }
    }

    private var server: EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration>? = null

    override fun start() {
        server = embeddedServer(Netty, EMBEDDED_SERVER_PORT, EMBEDDED_SERVER_HOST) {
            setupServer()
            configureRouting()
        }.start(wait = false)
    }

    override fun stop() {
        server?.stop()
    }

    private fun Application.setupServer() {
        install(ContentNegotiation) {
            json(baseJson)
        }
        install(Resources)
    }

    private fun Application.configureRouting() {
        routing {
            get<Restaurants> {
                call.respondText(
                    readResourceFile("restaurants.json"),
                    ContentType.Application.Json,
                    HttpStatusCode.OK
                )
            }
        }
    }

    companion object {
        private const val EMBEDDED_SERVER_PORT = 8080
        private const val EMBEDDED_SERVER_HOST = "localhost"
    }
}
