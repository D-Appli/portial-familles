package fr.dappli.portailfamilles.feature.reservation.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.dappli.portailfamilles.core.domain.model.form.SubCategory
import fr.dappli.portailfamilles.feature.reservation.R
import fr.dappli.portailfamilles.feature.reservation.navigation.ReservationSubCategoryRoute
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ReservationCard(
    name: String,
    @DrawableRes headerImageResId: Int,
    subCategories: ImmutableList<SubCategory>,
    onSubCategoryClick: (ReservationSubCategoryRoute) -> Unit
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = name,
            style = MaterialTheme.typography.titleLarge
        )
        HeaderImage(headerImageResId)

        subCategories.forEach { subCategory ->
            key(subCategory.id) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    onClick = {
                        onSubCategoryClick(
                            ReservationSubCategoryRoute(
                                subCategory.id,
                                subCategory.name
                            )
                        )
                    }
                ) {

                    ListItem(
                        headlineContent = {
                            Text(
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                text = subCategory.name,
                            )
                        },
                        supportingContent = {
                            Column {
                                Text(
                                    overflow = TextOverflow.Ellipsis,
                                    text = subCategory.description
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(text = "de ${subCategory.startDate}")
                                    Text(text = "à ${subCategory.startDate}")
                                }
                            }
                        },
                        trailingContent = {
                            Icon(
                                Icons.Filled.AddCircle,
                                contentDescription = null,
                                modifier = Modifier.padding(top = 22.dp),
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderImage(
    @DrawableRes iconResId: Int
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            ),
        contentScale = ContentScale.Crop,
        painter = painterResource(iconResId),
        contentDescription = null,
    )
}

@Preview
@Composable
private fun ReservationCardPreview() {
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
}
