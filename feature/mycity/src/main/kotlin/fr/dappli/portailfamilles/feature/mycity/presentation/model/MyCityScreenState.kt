package fr.dappli.portailfamilles.feature.mycity.presentation.model

import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import fr.dappli.portailfamilles.core.presentation.state.State
import kotlinx.collections.immutable.ImmutableList

sealed class MyCityScreenState : State {

    data object None : MyCityScreenState()

    data object Loading : MyCityScreenState()

    data object Error : MyCityScreenState()

    data class Content(
        val restaurants: ImmutableList<Restaurant>
    ) : MyCityScreenState()
}
