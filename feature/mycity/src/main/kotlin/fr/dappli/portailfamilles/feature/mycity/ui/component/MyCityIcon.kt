package fr.dappli.portailfamilles.feature.mycity.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun CityIcon(
    modifier: Modifier = Modifier,
    imageUrl: String,
    @DrawableRes emptyIconResId: Int
) {
    if (imageUrl.isEmpty()) {
        Icon(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .size(96.dp)
                .padding(16.dp),
            imageVector = ImageVector.vectorResource(emptyIconResId),
            // decorative image
            contentDescription = null,
        )
    } else {
        AsyncImage(
            modifier = Modifier.size(96.dp),
            imageUrl = imageUrl,
        )
    }
}
