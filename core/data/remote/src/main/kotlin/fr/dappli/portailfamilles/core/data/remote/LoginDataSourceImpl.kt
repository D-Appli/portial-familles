package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.api.LoginDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.Serializable

class LoginDataSourceImpl(
    private val client: HttpClient
) : LoginDataSource {
    override suspend fun login(username: String, password: String) {
        val user = User(username, password)
        client.post {
            url {
                path("login")
            }
            contentType(ContentType.Application.Json)
            setBody(user)
        }
    }
}

@Serializable
data class User(
    val username: String,
    val password: String
)
