package fr.dappli.portailfamilles.feature.mycity.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fr.dappli.portailfamilles.feature.mycity.R

@Composable
fun MyCityCard(
    title: String,
    description: String?,
    tags: String?,
    imageUrl: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(Modifier.height(96.dp)) {
            CityIcon(
                imageUrl = imageUrl ?: "",
                emptyIconResId = R.drawable.ic_restaurant
            )
            ListItem(
                modifier = Modifier.height(96.dp),
                headlineContent = {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = title
                    )
                },
                overlineContent = {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = tags ?: ""
                    )
                },
                supportingContent = {
                    Text(
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = description ?: ""
                    )
                },
            )
        }
    }
}
