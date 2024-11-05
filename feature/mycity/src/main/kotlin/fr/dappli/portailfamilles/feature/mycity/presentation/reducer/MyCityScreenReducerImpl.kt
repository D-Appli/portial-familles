package fr.dappli.portailfamilles.feature.mycity.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.mycity.presentation.model.MyCityScreenState
import fr.dappli.portailfamilles.feature.mycity.presentation.model.MyCityScreenState.Content
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenAction.AddContent
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenAction.SetContent
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenAction.SetError
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenAction.SetLoading
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
            is SetLoading -> onSetLoading(action, currentState)
            is SetContent -> onSetContent(action, currentState)
            is AddContent -> onAddContent(action, currentState)
            is SetError -> onSetError(action, currentState)
        }
    }

    private fun onSetLoading(action: SetLoading, currentState: MyCityScreenState): MyCityScreenState {
        val state = currentState as? Content ?: return MyCityScreenState.Loading
        return state.copy(isLoading = true)
    }

    private fun onSetContent(action: SetContent, currentState: MyCityScreenState): MyCityScreenState {
        return Content(
            isLoading = false,
            restaurants = action.restaurants.toImmutableList(),
            loadMoreItems = action.callToActions.loadMoreItems
        )
    }

    private fun onAddContent(action: AddContent, currentState: MyCityScreenState): MyCityScreenState {
        val state = currentState as? Content ?: return currentState
        val allRestaurants = state.restaurants.toMutableList().apply { addAll(action.restaurants) }
        return state.copy(isLoading = false, restaurants = allRestaurants.toImmutableList())
    }

    private fun onSetError(action: SetError, currentState: MyCityScreenState): MyCityScreenState {
        return MyCityScreenState.Error
    }
}
