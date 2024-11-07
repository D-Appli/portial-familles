package fr.dappli.portailfamilles.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import fr.dappli.portailfamilles.R
import fr.dappli.portailfamilles.core.domain.model.form.FormId
import fr.dappli.portailfamilles.feature.indentity.navigation.AccountRoute
import fr.dappli.portailfamilles.feature.mycity.navigation.MyCityRoute
import fr.dappli.portailfamilles.feature.receipts.navigation.ReceiptsRoute
import fr.dappli.portailfamilles.feature.reservation.navigation.ReservationCategoriesRoute
import kotlin.reflect.KClass

@Stable
enum class TopLevelDestination(
    val formId: FormId,
    @StringRes
    val screenNameResId: Int,
    @DrawableRes
    val filledImageResId: Int,
    @DrawableRes
    val outlinedImageResId: Int,
    val route: KClass<*>,
) {
    RESERVATION(
        FormId.RESERVATION,
        R.string.reservation_screen_name,
        R.drawable.ic_shopping_cart_filled,
        R.drawable.ic_shopping_cart_outlined,
        ReservationCategoriesRoute::class
    ),
    RECEIPTS(
        FormId.RECEIPTS,
        R.string.receipt_screen_name,
        R.drawable.ic_receipt_filled,
        R.drawable.ic_receipt_outlined,
        ReceiptsRoute::class
    ),
    ACCOUNT(
        FormId.ACCOUNT,
        R.string.account_screen_name,
        R.drawable.ic_person_filled,
        R.drawable.ic_person_outlined,
        AccountRoute::class
    ),
    MY_CITY(
        FormId.MY_CITY,
        R.string.my_city_screen_name,
        R.drawable.ic_city_filled,
        R.drawable.ic_city_outlined,
        MyCityRoute::class
    )
}
