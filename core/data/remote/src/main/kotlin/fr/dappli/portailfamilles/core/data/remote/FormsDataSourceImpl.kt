package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.api.FormsDataSource
import fr.dappli.portailfamilles.core.data.model.form.Forms
import fr.dappli.portailfamilles.core.data.model.form.UserId
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import javax.inject.Inject

class FormsDataSourceImpl @Inject constructor(
    private val client: HttpClient
): FormsDataSource {

    override suspend fun getForms(userId: String): Forms {

        val r = client.get("http://localhost:8080/restaurants")
        println("andrei: ${r.body<String>()}")

        val payload = UserId(userId)
        val response = client.post {
            url { path(PATH) }
            contentType(ContentType.Application.Json)
            setBody(payload)
        }
        return response.body()
    }

    private companion object {
        const val PATH = "portail/formulaires"
    }
}
