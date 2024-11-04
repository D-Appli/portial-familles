package fr.dappli.portailfamilles.feature.mycity.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.mycity.presentation.model.MyCityScreenState
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class MyCityScreenReducerImpl @Inject constructor(
    dispatcherProvider: DispatcherProvider,
) : MyCityScreenReducer(
    initialState = MyCityScreenState.None,
    dispatcherProvider = dispatcherProvider
) {

    override fun reduce(
        action: MyCityScreenAction,
        currentState: MyCityScreenState
    ): MyCityScreenState {
        return when (action) {
            MyCityScreenAction.SetLoading -> MyCityScreenState.Loading
            is MyCityScreenAction.SetContent -> MyCityScreenState.Content(
                action.restaurants.toImmutableList()
            )

            MyCityScreenAction.SetError -> MyCityScreenState.Error
        }
    }
}
