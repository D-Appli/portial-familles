package fr.dappli.portailfamilles.core.data.repository

import fr.dappli.portailfamilles.core.data.api.PersistenceKey
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.domain.irepository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val persistentDataSource: PersistentDataSource,
    private val userDataSource: UserDataSource,
) : AuthenticationRepository {

    override fun getToken(): Flow<String?> =
        persistentDataSource.observeString(PersistenceKey.TOKEN.name)

    override suspend fun getUserId(): String? =
        persistentDataSource.getString(PersistenceKey.USER_ID.name)

    override suspend fun getUser(userId: String): String =
        userDataSource.getUser(userId)
}
