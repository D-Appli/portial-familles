package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.api.LoginDataSource

class LoginDataSourceImpl : LoginDataSource {
    override suspend fun login(username: String, password: String) {
        println("todo")
    }
}
