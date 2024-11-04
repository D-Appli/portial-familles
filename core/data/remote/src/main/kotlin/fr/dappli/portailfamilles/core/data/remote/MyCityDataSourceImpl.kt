package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.api.MyCityDataSource
import fr.dappli.portailfamilles.core.data.model.mycity.Restaurants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path
import javax.inject.Inject

class MyCityDataSourceImpl @Inject constructor(
    private val client: HttpClient // TODO non auth client should be used
) : MyCityDataSource {

    override suspend fun getRestaurants(offset: Int, limit: Int): Restaurants {
        val response = client.get {
            url {
                protocol = URLProtocol.HTTP
                host = HOST
                port = PORT
                path(PATH)
                parameter(OFFSET_PARAM, offset)
                parameter(LIMIT_PARAM, limit)
            }
        }
        return response.body()
    }

    private companion object {
        const val HOST = "localhost"
        const val PORT = 8080
        const val PATH = "restaurants"

        const val OFFSET_PARAM = "offset"
        const val LIMIT_PARAM = "limit"
    }
}
