package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.FormRepository
import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.domain.model.form.FormId
import javax.inject.Inject

class GetReservationCategoriesUseCase @Inject constructor(
    private val repository: FormRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getForms().find {
            it.formId == FormId.RESERVATION
        }?.categories ?: return emptyList()
    }
}
