package com.carpe.quicknotes.model

import com.carpe.pincodelayout.model.IPinCodeInteractor
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PinCodeInteractor @Inject constructor(
    private val pinCodeRepository: PinCodeRepository
) : IPinCodeInteractor {
    override fun setCurrentTimeMs(): Completable {
        TODO("Not yet implemented")
    }

    override fun setPinCode(): Completable {
        return Completable.complete()
    }

    override fun checkPinCode(): Single<Boolean> {
        return Single.just(true)
    }
}