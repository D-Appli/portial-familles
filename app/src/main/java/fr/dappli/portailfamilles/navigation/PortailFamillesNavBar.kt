package fr.dappli.portailfamilles.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.R
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState
import fr.dappli.portailfamilles.presentation.navbar.viewmodel.NavBarViewModel
import fr.dappli.portailfamilles.presentation.navbar.viewmodel.NavBarViewModelImpl

@Composable
fun PortailFamillesNavBar(
    viewModel: NavBarViewModel = hiltViewModel<NavBarViewModelImpl>()
) {
    // TODO add animated content pop-up
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    when (val currentState = state) {
        NavBarState.None -> Unit
        is NavBarState.Content -> {
            var selectedItem by remember { mutableIntStateOf(0) }
            val items = currentState.items//listOf("Reservation", "Mes Factures", "Mon Compte")

            val selectedIcons = listOf(
                ImageVector.vectorResource(R.drawable.ic_shopping_cart_filled),
                ImageVector.vectorResource(R.drawable.ic_receipt_filled),
                Icons.Filled.Person
            )
            val unselectedIcons = listOf(
                ImageVector.vectorResource(R.drawable.ic_shopping_cart_outlined),
                ImageVector.vectorResource(R.drawable.ic_receipt_outlined),
                Icons.Outlined.Person
            )

            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    }
}