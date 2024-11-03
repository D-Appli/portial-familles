package fr.dappli.portailfamilles.server.routing

import io.ktor.resources.Resource

@Resource("/restaurants")
class AllRestaurants

@Resource("/restaurants")
data class PageRestaurants(
    val offset: Int,
    val limit: Int
)
