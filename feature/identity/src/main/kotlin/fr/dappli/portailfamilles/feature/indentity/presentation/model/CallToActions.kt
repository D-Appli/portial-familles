package fr.dappli.portailfamilles.feature.indentity.presentation.model

data class CallToActions(
    val onSignedIn: (userId: String, token: String) -> Unit
)
