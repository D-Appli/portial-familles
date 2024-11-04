package fr.dappli.portailfamilles.server.routing

import io.ktor.resources.Resource

@Resource("/$RESTAURANTS_PATH")
class AllRestaurants

@Resource("/$RESTAURANTS_PATH")
data class PageRestaurants(
    val offset: Int,
    val limit: Int
)

const val RESTAURANTS_PATH = "restaurants"
