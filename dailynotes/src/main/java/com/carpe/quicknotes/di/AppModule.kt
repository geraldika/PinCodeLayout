package com.carpe.quicknotes.di

import android.content.Context
import com.carpe.quicknotes.model.PinCodeInteractor
import com.carpe.quicknotes.model.PinCodeRepository
import com.carpe.quicknotes.model.PincodeLocalDataSource
import com.carpe.pincodelayout.model.IPinCodeInteractor
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)

        bind(PincodeLocalDataSource::class.java).to(PincodeLocalDataSource::class.java)
            .singletonInScope()
        bind(PinCodeRepository::class.java).to(PinCodeRepository::class.java).singletonInScope()
        bind(IPinCodeInteractor::class.java).to(PinCodeInteractor::class.java).singletonInScope()
    }
}