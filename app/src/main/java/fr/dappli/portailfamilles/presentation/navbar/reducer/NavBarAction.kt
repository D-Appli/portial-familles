package fr.dappli.portailfamilles.presentation.navbar.reducer

import fr.dappli.portailfamilles.core.domain.model.form.Form
import fr.dappli.portailfamilles.core.presentation.state.Action

sealed class NavBarAction : Action {
     data class SetContent(
          val forms: List<Form>
     ) : NavBarAction()
}
