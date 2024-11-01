package fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.feature.indentity.presentation.state.IdentityScreenState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class IdentityScreenViewModelImpl @Inject constructor(
) : IdentityScreenViewModel() {

    override val stateFlow: StateFlow<IdentityScreenState>
        get() = TODO("Not yet implemented")
}
