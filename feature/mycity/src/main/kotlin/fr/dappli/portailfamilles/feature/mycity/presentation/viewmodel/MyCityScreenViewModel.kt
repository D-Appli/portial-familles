package fr.dappli.portailfamilles.feature.mycity.presentation.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.feature.mycity.presentation.model.MyCityScreenState
import kotlinx.coroutines.flow.StateFlow

abstract class MyCityScreenViewModel : ViewModel() {
    abstract val stateFlow: StateFlow<MyCityScreenState>
}
