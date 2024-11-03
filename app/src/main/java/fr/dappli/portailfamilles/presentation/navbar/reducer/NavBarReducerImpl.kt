package fr.dappli.portailfamilles.presentation.navbar.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class NavBarReducerImpl @Inject constructor(
    dispatcherProvider: DispatcherProvider,
) : NavBarReducer(NavBarState.None, dispatcherProvider) {

    override fun reduce(action: NavBarAction, currentState: NavBarState): NavBarState {
        return when (action) {
            is NavBarAction.SetContent -> {
                val items = action.forms.map { it.label }.subList(0, 3).toImmutableList()
                NavBarState.Content(items)
            }
        }
    }
}
