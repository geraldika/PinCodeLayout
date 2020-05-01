package com.carpediem.dailynotes

import android.app.Application
import com.carpediem.dailynotes.di.AppModule
import com.carpediem.dailynotes.di.Scopes
import com.carpediem.dailynotes.extensions.isDebug
import com.carpediem.dailynotes.extensions.isProduction
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class DailyNotesApp : Application() {

    lateinit var scope: Scope

    override fun onCreate() {
        super.onCreate()
        initToothpick()
        initTimber()
    }

    private fun initToothpick() {
        if (isDebug()) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction())
        }

        scope = Toothpick.openScope(Scopes.Application)
        scope.installModules(AppModule(this))
    }

    private fun initTimber() {
        if (isProduction()) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(Timber.DebugTree())
        }
    }
}

//todo
//fingerprint
//points
//lastic