package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.data.model.user.UserIdModel
import fr.dappli.portailfamilles.core.data.model.user.UserModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : UserDataSource {

    override suspend fun getUser(userId: String): String {
        val response = client.post {
            url {
                path(PATH)
            }
            contentType(ContentType.Application.Json)
            setBody(UserIdModel(userId))
        }
        val userModel = response.body<UserModel>()
        val civilite = userModel.civilite ?: ""
        val nom = userModel.nom ?: ""
        val prenom = userModel.prenom ?: ""
        val mail = userModel.mail ?: ""
        return "$civilite $nom $prenom $mail".trim()
    }

    private companion object {
        const val PATH = "/portail/token/utilisateur"
    }
}
