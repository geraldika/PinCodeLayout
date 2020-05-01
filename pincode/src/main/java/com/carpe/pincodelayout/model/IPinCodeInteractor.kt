package com.carpe.pincodelayout.model

import io.reactivex.Completable

interface IPinCodeInteractor {

    fun setCurrentTimeMs(): Completable
    fun setPinCode(): Completable
    fun checkPinCode(): Completable
}