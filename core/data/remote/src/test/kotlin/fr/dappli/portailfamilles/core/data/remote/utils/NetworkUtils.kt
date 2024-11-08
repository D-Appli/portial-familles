package fr.dappli.portailfamilles.core.data.remote.utils

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

fun mockEngineFromJson(
    fileName: String,
    status: HttpStatusCode = HttpStatusCode.OK,
): MockEngine = MockEngine { request ->
    println(request.url)
    respond(
        content = ByteReadChannel(readBinaryFileFromResources(fileName)),
        status = status,
        headers = headersOf(HttpHeaders.ContentType, "application/json")
    )
}
