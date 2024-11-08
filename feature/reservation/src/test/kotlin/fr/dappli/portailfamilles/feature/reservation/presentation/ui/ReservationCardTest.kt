package fr.dappli.portailfamilles.feature.reservation.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.TextUnit
import fr.dappli.portailfamilles.core.domain.model.form.SubCategory
import fr.dappli.portailfamilles.feature.reservation.R
import fr.dappli.portailfamilles.feature.reservation.ui.component.ReservationCard
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReservationCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `verify category label and subcategory name visibility`() {
        with(composeTestRule) {
            var fontSize: TextUnit? = null
            setContent {
                ReservationCard(
                    name = "Péri-scolaire",
                    R.drawable.school,
                    listOf(
                        SubCategory(
                            id = 0,
                            name = "Décembre 2024",
                            description = "Cantine, garderie, accompagnements aux lecons",
                            startDate = "01/11/2024",
                            endDate = "31/11/2024"
                        )
                    ).toImmutableList()
                ) {}

                fontSize = MaterialTheme.typography.titleLarge.fontSize
            }

            // verify card title
            onNode(hasText("Péri-scolaire")).apply {
                assertIsDisplayed()
                assert(hasFontSize(fontSize!!))
            }

            // verify item subcategory name
            onNode(hasText("Décembre 2024")).assertIsDisplayed()
        }
    }

    @Test
    fun `verify click on subcategory item`() {
        with(composeTestRule) {
            var clickCounter = 0
            setContent {
                ReservationCard(
                    name = "Péri-scolaire",
                    R.drawable.school,
                    listOf(
                        SubCategory(
                            id = 0,
                            name = "Décembre 2024",
                            description = "Cantine, garderie, accompagnements aux lecons",
                            startDate = "01/11/2024",
                            endDate = "31/11/2024"
                        )
                    ).toImmutableList()
                ) { clickCounter++ }
            }

            // verify click
            assert(clickCounter == 0)
            onNode(hasText("Décembre 2024")).performClick()
            assert(clickCounter == 1)
        }
    }

}
