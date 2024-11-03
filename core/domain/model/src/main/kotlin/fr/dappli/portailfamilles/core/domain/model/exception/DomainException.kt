package fr.dappli.portailfamilles.core.domain.model.exception

sealed class DomainException(errorMessage: String) : Throwable(message = errorMessage) {
    class ServerException(errorMessage: String, val code: Int) : DomainException(errorMessage)
    class PageError(errorMessage: String) : DomainException(errorMessage)
}
