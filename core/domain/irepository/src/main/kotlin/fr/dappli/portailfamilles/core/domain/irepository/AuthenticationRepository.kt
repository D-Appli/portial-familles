package fr.dappli.portailfamilles.core.domain.irepository

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun getToken(): Flow<String?>
    suspend fun clearToken()
    suspend fun getUserId(): String?
    suspend fun getUser(userId: String): String // TODO replace by model
}
