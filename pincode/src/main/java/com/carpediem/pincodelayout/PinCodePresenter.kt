package com.carpediem.pincodelayout

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.carpediem.pincodelayout.model.IPinCodeInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class PinCodePresenter<V : PinCodeView> constructor(
    private val pinCodeInteractor: IPinCodeInteractor
) : MvpPresenter<V>() {

    val pinCodeDigitsSize = 4
    var pinCode = mutableListOf<Int>()

    fun initDigits() {
        val items = Utils.generatePinCodeItems()
        viewState.showPinCodeItems(items)
    }

    @SuppressLint("CheckResult")
    fun onNextDigit(digit: Int) {
        Log.d("TestB", "onNextDigit $digit ")
        pinCode.add(digit)
        if (pinCode.size == 4) {
            pinCodeInteractor
                .checkPinCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {}, {}
                )
        }
    }
}
