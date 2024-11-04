package fr.dappli.portailfamilles.feature.mycity.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.core.presentation.state.StateReducer
import fr.dappli.portailfamilles.feature.mycity.presentation.model.MyCityScreenState

abstract class MyCityScreenReducer(
    initialState: MyCityScreenState,
    dispatcherProvider: DispatcherProvider,
)  : StateReducer<MyCityScreenState, MyCityScreenAction>(initialState, dispatcherProvider)
