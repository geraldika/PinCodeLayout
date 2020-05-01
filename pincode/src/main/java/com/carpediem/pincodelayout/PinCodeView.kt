package com.carpediem.pincodelayout

import com.arellomobile.mvp.MvpView
import com.carpediem.pincodelayout.model.PinCodeItem

interface PinCodeView: MvpView {

    fun showPinCodeSuccessfullySaved()
    fun showErrorWhilePinCode()
    fun showPinCodeItems(items: List<PinCodeItem>)
}