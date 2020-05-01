package com.carpe.pincodelayout.presentation

import com.arellomobile.mvp.MvpView
import com.carpe.pincodelayout.model.Dot
import com.carpe.pincodelayout.model.PinCodeItem

interface PinCodeView : MvpView {

    fun showPinCodeSuccessfullySaved()
    fun showErrorWhilePinCode()
    fun showPinCodeItems(items: List<PinCodeItem>)
    fun showDots(dots: List<Dot>)
    fun showActiveDot()
    fun showInactiveDot()
    fun showFingerPrintCheck()
}