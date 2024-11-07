package fr.dappli.portailfamilles.core.domain.model.form

data class Category(
    val categoryId: CategoryId,
    val name: String,
    val items: List<SubCategory>
)

data class SubCategory(
    val id: String,
    val name: String,
    val description: String,
    val startDate: String, // TODO could be converted to kotlinx LocalDate
    val endDate: String,
)

enum class CategoryId(val code: String) {
    PERI_SCOLAIRE("PERI-SCOLAIRE"),
    EXTRA_SCOLAIRE("EXTRA-SCOLAIRE"),
    ARTELIER_INISPORT("ARTELIER - INISPORT"),
    MES_LIENS_UTILES("MES LIENS UTILES"),
    UNKNOWN("UNKNOWN"),
}
