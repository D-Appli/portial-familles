package fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.feature.indentity.presentation.state.IdentityScreenState
import kotlinx.coroutines.flow.StateFlow

abstract class IdentityScreenViewModel : ViewModel() {
    abstract val stateFlow: StateFlow<IdentityScreenState>
}
