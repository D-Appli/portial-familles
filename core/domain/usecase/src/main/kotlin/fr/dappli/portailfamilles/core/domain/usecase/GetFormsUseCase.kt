package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.FormRepository
import fr.dappli.portailfamilles.core.domain.model.form.Form
import fr.dappli.portailfamilles.core.domain.model.form.FormId
import javax.inject.Inject

class GetFormsUseCase @Inject constructor(
    private val repository: FormRepository
) {
    suspend operator fun invoke(): List<Form> {
        return repository.getForms()
            .sortedBy {
                it.formId.order
            }.toMutableList().apply {
                add(Form(FormId.MY_CITY, MY_CITY_LABEL))
            }.map {
                val label = when (it.formId) {
                    FormId.RESERVATION -> RESERVATION_LABEL
                    FormId.RECEIPTS -> RECEIPTS_LABEL
                    FormId.ACCOUNT -> ACCOUNT_LABEL
                    FormId.MY_CITY -> MY_CITY_LABEL
                }
                it.copy(label = label)
            }
    }

    private companion object {
        const val RESERVATION_LABEL = "Réservation"
        const val RECEIPTS_LABEL = "Mes Factures"
        const val ACCOUNT_LABEL = "Mon Compte"
        const val MY_CITY_LABEL = "Ma Ville"
    }
}

