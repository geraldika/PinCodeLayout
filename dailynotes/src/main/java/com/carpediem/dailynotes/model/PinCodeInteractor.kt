package com.carpediem.dailynotes.model

import com.carpediem.pincodelayout.model.IPinCodeInteractor
import io.reactivex.Completable
import javax.inject.Inject

class PinCodeInteractor @Inject constructor(
    private val pinCodeRepository: PinCodeRepository
) : IPinCodeInteractor {
    override fun setCurrentTimeMs(): Completable {
        TODO("Not yet implemented")
    }

    override fun setPinCode(): Completable {
        TODO("Not yet implemented")
    }

    override fun checkPinCode(): Completable {
        TODO("Not yet implemented")
    }
}