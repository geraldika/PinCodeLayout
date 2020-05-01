package com.carpe.pincodelayout.model

import io.reactivex.Completable
import io.reactivex.Single

interface IPinCodeInteractor {

    fun setCurrentTimeMs(): Completable
    fun setPinCode(): Completable
    fun checkPinCode(): Single<Boolean>
}