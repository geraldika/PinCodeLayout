package com.carpe.quicknotes.model

import com.carpe.pincodelayout.model.IPinCodeInteractor
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PinCodeInteractor @Inject constructor(
    private val pinCodeRepository: PinCodeRepository
) : IPinCodeInteractor {

    override fun isPinCodeExist(): Single<Boolean> {
        return Single.just(true)
    }

    override fun setCurrentTimeMs(): Completable {
        return Completable.complete()
    }

    override fun setPinCode(): Completable {
        return Completable.complete()
    }

    override fun createPinCode(pinCode: String): Single<Boolean> {
        return Single.just(true)
    }

    override fun checkPinCode(pinCode: String): Single<Boolean> {
        return Single.just(true)
    }
}