package fr.dappli.portailfamilles.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState
import fr.dappli.portailfamilles.presentation.navbar.viewmodel.NavBarViewModel
import fr.dappli.portailfamilles.presentation.navbar.viewmodel.NavBarViewModelImpl
import fr.dappli.portailfamilles.ui.PortailFamillesAppState

@Composable
fun PortailFamillesNavBar(
    appState: PortailFamillesAppState,
    viewModel: NavBarViewModel = hiltViewModel<NavBarViewModelImpl>()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    when (val currentState = state) {
        NavBarState.None -> Unit
        is NavBarState.Content -> {
            var selectedItem by remember { mutableIntStateOf(0) }
            NavigationBar {
                currentState.items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(if (selectedItem == index) item.filledImageResId else item.outlinedImageResId),
                                contentDescription = item.name
                            )
                        },
                        label = { Text(stringResource(item.screenNameResId)) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            appState.navigateToTopLevelDestination(item)
                        }
                    )
                }
            }
        }
    }
}
