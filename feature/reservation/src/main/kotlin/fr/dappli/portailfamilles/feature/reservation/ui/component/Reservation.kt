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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fr.dappli.portailfamilles.core.kotlin.titleCase
import fr.dappli.portailfamilles.feature.reservation.R

@Composable
fun ReservationCard() {
    Column {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "PERI-SCOLAIRE".titleCase(),
            style = MaterialTheme.typography.headlineSmall
        )
        HeaderImage(R.drawable.school)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = {}
        ) {
            ListItem(
                headlineContent = {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = "NOVEMBRE 2024".titleCase(),
                    )
                },
                supportingContent = {
                    Column {
                        Text(
                            overflow = TextOverflow.Ellipsis,
                            text = "Cantine, garderie, accompagnements aux lecons".titleCase()
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(text = "de 01/11/2024")
                            Text(text = "à 31/12/2024")
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
