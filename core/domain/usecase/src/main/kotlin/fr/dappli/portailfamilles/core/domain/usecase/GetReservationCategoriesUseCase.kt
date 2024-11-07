package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.FormRepository
import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.domain.model.form.CategoryId
import fr.dappli.portailfamilles.core.domain.model.form.FormId
import javax.inject.Inject

class GetReservationCategoriesUseCase @Inject constructor(
    private val repository: FormRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getForms().find {
            it.formId == FormId.RESERVATION
        }?.categories?.filter {
            // TODO this MES_LIENS_UTILES category contains only html content
            // either we can try to display as it is, or map to new items
            it.categoryId != CategoryId.MES_LIENS_UTILES
        } ?: return emptyList()
    }
}
