package fr.dappli.portailfamilles.core.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fr.dappli.portailfamilles.core.ui.R

@Composable
fun WorkInProgress() {
    var showContent by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = showContent,
        enter = fadeIn(tween(1000)) + expandVertically(tween(durationMillis = 1000)),
        exit = fadeOut(tween(1000)) + shrinkVertically(tween(durationMillis = 1000)),
    ) {
        var columnHeight by remember { mutableStateOf(0) }
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    columnHeight = layoutCoordinates.size.height
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier
                    .height((columnHeight / 3).dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.work_in_progress),
                contentDescription = null,
            )
        }
    }
    LaunchedEffect(Unit) {
        showContent = true
    }
}
