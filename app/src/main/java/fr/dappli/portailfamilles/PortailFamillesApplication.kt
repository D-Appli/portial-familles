package fr.dappli.portailfamilles

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PortailFamillesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // TODO initialise coil / image loader
    }
}
