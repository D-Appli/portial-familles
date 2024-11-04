package fr.dappli.portailfamilles.server

import fr.dappli.portailfamilles.core.data.model.mycity.Restaurant
import fr.dappli.portailfamilles.core.data.model.mycity.Restaurants
import fr.dappli.portailfamilles.server.routing.AllRestaurants
import fr.dappli.portailfamilles.server.routing.PageRestaurants
import fr.dappli.portailfamilles.server.routing.RESTAURANTS_PATH
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
import io.ktor.server.response.respond
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

    private var restaurants: MutableList<Restaurant>

    init {
        val json = readResourceFile("$RESTAURANTS_PATH.json")
        val restaurantsObject = Json.decodeFromString<Restaurants>(json)
        restaurants = restaurantsObject.restaurants?.filterNotNull()?.toMutableList() ?: mutableListOf()
    }

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
            get<AllRestaurants> {
                call.respond(
                    HttpStatusCode.OK,
                    Restaurants(restaurants)
                )
            }
            get<PageRestaurants> {
                val fromIndex = it.offset
                val toIndexExclusive = it.offset + it.limit
                if (toIndexExclusive > restaurants.size) {
                    val diff = toIndexExclusive - restaurants.size
                    repeat(diff) {
                        val humanNumber = restaurants.size + 1
                        restaurants.add(
                            Restaurant(
                                title = "Mock restaurant $humanNumber",
                                address = "Avenue du bonheur $humanNumber",
                                description = "Très bon restaurant $humanNumber",
                                imageUrl = null
                            )
                        )
                    }
                    restaurants
                }
                val restos = restaurants.subList(fromIndex, toIndexExclusive)
                call.respond(HttpStatusCode.OK, Restaurants(restos))
            }
        }
    }

    companion object {
        private const val EMBEDDED_SERVER_PORT = 8080
        private const val EMBEDDED_SERVER_HOST = "localhost"
    }
}
