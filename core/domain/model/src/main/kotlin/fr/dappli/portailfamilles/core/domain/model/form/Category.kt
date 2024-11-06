package fr.dappli.portailfamilles.core.domain.model.form

data class Category(
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
