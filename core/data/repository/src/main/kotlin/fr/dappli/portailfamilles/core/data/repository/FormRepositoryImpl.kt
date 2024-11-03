package fr.dappli.portailfamilles.core.data.repository

import fr.dappli.portailfamilles.core.data.api.FormsDataSource
import fr.dappli.portailfamilles.core.data.api.PersistenceKey
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import fr.dappli.portailfamilles.core.data.repository.mapper.FormMapper
import fr.dappli.portailfamilles.core.data.repository.mapper.toDomainException
import fr.dappli.portailfamilles.core.domain.irepository.FormRepository
import fr.dappli.portailfamilles.core.domain.model.form.Form
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FormRepositoryImpl @Inject constructor(
    private val formsDataSource: FormsDataSource,
    private val persistentDataSource: PersistentDataSource,
    private val mapper: FormMapper
): FormRepository {

    private val mutex = Mutex()
    private val inMemoryCache = mutableMapOf<String, List<Form>>()

    override suspend fun getForms(forceRefresh: Boolean): List<Form> {
        return try {
            val userId = persistentDataSource.getString(PersistenceKey.USER_ID.name) ?:
                throw InternalError("No user id to get forms")

            val cache = mutex.withLock { inMemoryCache[userId] }
            if (forceRefresh.not() && cache != null) {
                cache
            } else {
                mapper.map(formsDataSource.getForms(userId)).also {
                    mutex.withLock { inMemoryCache[userId] = it }
                }
            }
        } catch (e: Throwable) {
            throw e.toDomainException()
        }
    }
}
