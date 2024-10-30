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
    override suspend fun login(username: String, password: String, captcha: String) {
        val loginModel = LoginModel(username, password, captcha)
        client.post {
            url {
                path("login")
            }
            contentType(ContentType.Application.Json)
            setBody(loginModel)
        }
    }
}

@Serializable
data class LoginModel(
    val username: String,
    val password: String,
    val captcha: String,
)
