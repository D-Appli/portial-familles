package fr.dappli.portailfamilles.presentation.navbar.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState
import kotlinx.coroutines.flow.StateFlow

abstract class NavBarViewModel : ViewModel() {
    abstract val stateFlow: StateFlow<NavBarState>
}
