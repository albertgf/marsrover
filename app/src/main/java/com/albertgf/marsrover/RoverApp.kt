package com.albertgf.marsrover

import android.app.Application
import com.albertgf.features.generator.featureUsersGenerator
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RoverApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@RoverApp)

            modules(listOf(
                featureUsersGenerator
            ))
        }
    }
}