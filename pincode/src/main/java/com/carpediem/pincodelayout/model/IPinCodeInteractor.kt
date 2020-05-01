package com.carpediem.pincodelayout.model

import io.reactivex.Completable
import io.reactivex.Single

interface IPinCodeInteractor {

    fun setCurrentTimeMs(): Completable
    fun setPinCode(): Completable
    fun checkPinCode(): Completable
}