package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.AuthenticationRepository
import fr.dappli.portailfamilles.core.domain.model.exception.DomainException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsUserAuthenticatedUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return flowOf(true)
//        return repository.getToken().map { token ->
//            val userId = repository.getUserId()
//            when {
//                token.isNullOrBlank() -> false
//                userId.isNullOrBlank() -> false
//                else -> {
//                    try {
//                        repository.getUser(userId)
//                        true
//                    } catch (e: DomainException.ServerException) {
//                        // OAuth2 refresh token is not implemented by the server,
//                        // so we need to clear previous token if there are 401/500 and other issues with
//                        // get user api
//                        repository.clearToken()
//                        false
//                    }
//                }
//            }
//        }
    }
}
