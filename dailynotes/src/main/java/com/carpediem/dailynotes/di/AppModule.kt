package com.carpediem.dailynotes.di

import android.content.Context
import com.carpediem.dailynotes.model.PinCodeInteractor
import com.carpediem.dailynotes.model.PinCodeRepository
import com.carpediem.dailynotes.model.PincodeLocalDataSource
import com.carpediem.pincodelayout.model.IPinCodeInteractor
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