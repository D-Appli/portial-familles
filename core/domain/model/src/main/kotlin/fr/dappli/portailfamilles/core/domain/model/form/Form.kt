package fr.dappli.portailfamilles.core.domain.model.form

data class Form(
    val formId: FormId,
    val label: String
)

enum class FormId(val id: Int, val order: Int) {
    RESERVATION(42, 1),
    RECEIPTS(44, 2),
    ACCOUNT(43, 3),
    MY_CITY(2024, 4)
}
