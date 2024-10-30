package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.api.UserDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.Serializable

class UserDataSourceImpl(
    private val client: HttpClient
) : UserDataSource {

    override suspend fun getUser(userId: String, token: String): String {
        val response = client.post {
            url {
                path(PATH)
            }
            headers {
                append(HttpHeaders.Authorization, "$BEARER_AUTH $token")
            }
            contentType(ContentType.Application.Json)
            setBody(UserIdModel(userId))
        }
        if (response.status.value == HttpStatusCode.OK.value) {
            val userModel = response.body<UserModel>()
            val civilite = userModel.civilite ?: ""
            val nom = userModel.nom ?: ""
            val prenom = userModel.prenom ?: ""
            return "$civilite $nom $prenom".trim()
        } else {
            throw ResponseException(response, response.body())
        }
    }

    private companion object {
        private const val BEARER_AUTH = "Bearer"
        const val PATH = "/portail/token/utilisateur"
    }
}

@Serializable
data class UserIdModel(
    val idfampass: String
)

@Serializable
data class UserModel(
    val code_retour: String?,
    val nom: String?,
    val prenom: String?,
    val mail: String?,
    val civilite: String?
)

