package com.carpe.quicknotes

import android.app.Application
import com.carpe.quicknotes.di.AppModule
import com.carpe.quicknotes.di.Scopes
import com.carpe.quicknotes.extensions.isDebug
import com.carpe.quicknotes.extensions.isProduction
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class QuickNotesApp : Application() {

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