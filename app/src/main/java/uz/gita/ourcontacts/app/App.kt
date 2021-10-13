package uz.gita.ourcontacts.app

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var instance : App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        instance = this
    }
}