package com.carpe.quicknotes.presentation

import com.arellomobile.mvp.InjectViewState
import com.carpe.pincodelayout.presentation.PinCodePresenter
import com.carpe.pincodelayout.model.IPinCodeInteractor
import javax.inject.Inject

@InjectViewState
class QuickNotesPinCodePresenter @Inject constructor(
    private val pinCodeInteractor: IPinCodeInteractor) :
    PinCodePresenter<QuickNotesPincodeView>(pinCodeInteractor) {


}