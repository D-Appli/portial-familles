package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.api.SignInDataSource
import fr.dappli.portailfamilles.core.data.remote.network.Network
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.Serializable
import javax.inject.Inject

class SignInDataSourceImpl @Inject constructor(
    private val network: Network
) : SignInDataSource {

    override fun clearAuthToken() {
        network.clearBearerToken()
    }

    // It can not be used right now, since captcha is not yeh hacked from js
    override suspend fun signIn(username: String, password: String, captcha: String) {
        val loginModel = LoginModel(username, password, captcha)
        network.client.post {
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
