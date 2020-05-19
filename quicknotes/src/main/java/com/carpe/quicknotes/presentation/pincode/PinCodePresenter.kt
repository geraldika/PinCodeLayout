package com.carpe.quicknotes.presentation.pincode

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PinCodePresenter @Inject constructor(

): MvpPresenter<PinCodeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}