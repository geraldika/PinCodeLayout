package com.carpe.quicknotes.di

import android.content.Context
import com.carpe.quicknotes.model.PinCodeInteractor
import com.carpe.quicknotes.model.PinCodeRepository
import com.carpe.quicknotes.model.PincodeLocalDataSource
import com.carpe.pincodelayout.model.IPinCodeInteractor
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        //Navigation
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        bind(Context::class.java).toInstance(context)

        bind(PincodeLocalDataSource::class.java).to(PincodeLocalDataSource::class.java)
            .singletonInScope()
        bind(PinCodeRepository::class.java).to(PinCodeRepository::class.java).singletonInScope()
        bind(IPinCodeInteractor::class.java).to(PinCodeInteractor::class.java).singletonInScope()
    }
}