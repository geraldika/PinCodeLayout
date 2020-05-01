package com.carpe.pincodelayout.model

import io.reactivex.Completable
import io.reactivex.Single

interface IPinCodeInteractor {

    fun isPinCodeExist(): Single<Boolean>
    fun setCurrentTimeMs(): Completable
    fun setPinCode(): Completable
    fun createPinCode(pinCode: String): Single<Boolean>
    fun checkPinCode(pinCode: String): Single<Boolean>

}