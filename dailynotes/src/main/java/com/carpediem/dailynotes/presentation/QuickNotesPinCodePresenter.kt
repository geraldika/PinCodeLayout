package com.carpediem.dailynotes.presentation

import com.arellomobile.mvp.InjectViewState
import com.carpediem.pincodelayout.PinCodePresenter
import com.carpediem.pincodelayout.model.IPinCodeInteractor
import javax.inject.Inject

@InjectViewState
class QuickNotesPinCodePresenter @Inject constructor(
    private val pinCodeInteractor: IPinCodeInteractor) :
    PinCodePresenter<QuickNotesPincodeView>(pinCodeInteractor) {


}