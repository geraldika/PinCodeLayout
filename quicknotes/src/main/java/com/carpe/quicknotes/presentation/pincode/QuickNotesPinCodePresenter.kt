package com.carpe.quicknotes.presentation.pincode

import com.arellomobile.mvp.InjectViewState
import com.carpe.pincodelayout.model.IPinCodeInteractor
import com.carpe.pincodelayout.presentation.PinCodePresenter
import javax.inject.Inject

@InjectViewState
class QuickNotesPinCodePresenter @Inject constructor(
    private val pinCodeInteractor: IPinCodeInteractor
) :
    PinCodePresenter<QuickNotesPincodeView>(pinCodeInteractor) {


}