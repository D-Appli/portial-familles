package fr.dappli.portailfamilles.core.data.repository

import fr.dappli.portailfamilles.core.data.api.FormsDataSource
import fr.dappli.portailfamilles.core.data.api.PersistenceKey
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import fr.dappli.portailfamilles.core.data.repository.mapper.FormMapper
import fr.dappli.portailfamilles.core.data.repository.mapper.toDomainException
import fr.dappli.portailfamilles.core.domain.irepository.FormRepository
import fr.dappli.portailfamilles.core.domain.model.form.Form
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor(
    private val formsDataSource: FormsDataSource,
    private val persistentDataSource: PersistentDataSource,
    private val mapper: FormMapper
): FormRepository {

    override suspend fun getForms(): List<Form> {
        return try {
            val userId = persistentDataSource.getString(PersistenceKey.USER_ID.name) ?:
                throw InternalError("No user id to get forms")
            mapper.map(formsDataSource.getForms(userId))
        } catch (e: Throwable) {
            throw e.toDomainException()
        }
    }
}
