package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsUserAuthenticatedUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.getToken().map { token ->
            val userId = repository.getUserId()
            when {
                token.isNullOrBlank() -> false
                userId.isNullOrBlank() -> false
                else -> {
                    repository.getUser(userId) // TODO handle 401, 500 exceptions
                    true
                }
            }
        }
    }
}
