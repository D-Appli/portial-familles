package fr.dappli.portailfamilles.navigation

import androidx.annotation.DrawableRes
import fr.dappli.portailfamilles.R
import fr.dappli.portailfamilles.core.domain.model.form.FormId
import fr.dappli.portailfamilles.feature.mycity.navigation.MyCityRoute
import fr.dappli.portailfamilles.feature.reservation.navigation.ReservationCategoriesRoute
import fr.dappli.portailfamilles.feature.reservation.navigation.ReservationRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val formId: FormId,
    @DrawableRes
    val filledImageResId: Int,
    @DrawableRes
    val outlinedImageResId: Int,
    val route: KClass<*>,
) {
    RESERVATION(
        FormId.RESERVATION,
        R.drawable.ic_shopping_cart_filled,
        R.drawable.ic_shopping_cart_outlined,
        ReservationCategoriesRoute::class // TODO replace me
    ),
    RECEIPTS(
        FormId.RECEIPTS,
        R.drawable.ic_receipt_filled,
        R.drawable.ic_receipt_outlined,
        ReservationRoute::class
    ),
    ACCOUNT(
        FormId.ACCOUNT,
        R.drawable.ic_person_filled,
        R.drawable.ic_person_outlined,
        ReservationRoute::class // TODO replace me
    ),
    MY_CITY(
        FormId.MY_CITY,
        R.drawable.ic_city_filled,
        R.drawable.ic_city_outlined,
        MyCityRoute::class
    )
}
