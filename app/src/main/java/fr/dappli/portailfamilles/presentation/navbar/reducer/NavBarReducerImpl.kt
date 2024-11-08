package fr.dappli.portailfamilles.presentation.navbar.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.navigation.TopLevelDestination
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class NavBarReducerImpl @Inject constructor(
    dispatcherProvider: DispatcherProvider,
) : NavBarReducer(NavBarState.None, dispatcherProvider) {

    override fun reduce(action: NavBarAction, currentState: NavBarState): NavBarState {
        return when (action) {
            is NavBarAction.SetContent -> {
                val items = action.forms.mapNotNull { item ->
                    TopLevelDestination.entries.find { it.formId == item.formId }
                }.subList(0, MAX_ITEMS).toImmutableList()
                NavBarState.Content(items)
            }

            NavBarAction.SetError -> NavBarState.None
        }
    }

    private companion object {
        const val MAX_ITEMS = 4
    }
}
