package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.FormRepository
import fr.dappli.portailfamilles.core.domain.model.form.Form
import javax.inject.Inject

class GetFormsUseCase @Inject constructor(
    private val repository: FormRepository
) {
    suspend operator fun invoke(): List<Form> = repository.getForms()
}
