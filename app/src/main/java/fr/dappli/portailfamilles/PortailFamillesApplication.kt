package fr.dappli.portailfamilles

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import fr.dappli.portailfamilles.server.Server
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class PortailFamillesApplication : Application() {

    @Inject
    lateinit var server: Server

    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch(Dispatchers.IO) {
            server.start()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        server.stop()
    }
}
