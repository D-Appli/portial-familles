package fr.dappli.portailfamilles.core.data.remote.network

import io.ktor.client.HttpClient


interface Network {
    val client: HttpClient
}
