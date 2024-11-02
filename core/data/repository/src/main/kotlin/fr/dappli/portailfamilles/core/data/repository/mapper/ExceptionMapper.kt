package fr.dappli.portailfamilles.core.data.repository.mapper

import fr.dappli.portailfamilles.core.domain.model.exception.DomainException
import io.ktor.client.plugins.ResponseException

fun Throwable.toDomainException(): Throwable {
    return when (this) {
        is ResponseException -> DomainException.ServerException(
            errorMessage = toString(),
            code = response.status.value
        )
        else -> this
    }
}
