package com.carpe.pincodelayout.presentation

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.carpe.pincodelayout.Utils
import com.carpe.pincodelayout.model.Dot
import com.carpe.pincodelayout.model.IPinCodeInteractor
import com.carpe.pincodelayout.model.PinCodeItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class PinCodePresenter<V : PinCodeView> constructor(
    private val pinCodeInteractor: IPinCodeInteractor
) : MvpPresenter<V>() {

    protected open var pinCodeDigitsSize = 0
    protected open var pinCodeDigits = mutableListOf<Int>()

    open fun initPinCodeView(pinCodeDigitsSize: Int) {
        this.pinCodeDigitsSize = pinCodeDigitsSize
        val items = Utils.generatePinCodeItems()
        val dotsList = List<Dot>(pinCodeDigitsSize) { Dot() }
        viewState.showDots(dotsList)
        viewState.showPinCodeItems(items)
    }

    @SuppressLint("CheckResult")
    open fun onNextDigit(item: PinCodeItem) {
        when (item) {
            is PinCodeItem.Digit -> {
                validatePinCode(item.digit)
            }
            is PinCodeItem.Eraser -> {
                eraseDigit()
            }
            else -> viewState.showFingerPrintCheck()
        }
    }

    protected open fun eraseDigit() {
        if (pinCodeDigits.isNotEmpty()) {
            pinCodeDigits.removeAt(pinCodeDigits.last())
            viewState.showInactiveDot()
        }
    }

    @SuppressLint("CheckResult")
    protected open fun validatePinCode(digit: Int) {
        viewState.showActiveDot()
        pinCodeDigits.add(digit)
        if (pinCodeDigits.size == pinCodeDigitsSize) {
            pinCodeInteractor
                .checkPinCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, { error ->
                    Log.e(TAG, "Error while pin code check", error)
                })
        }
    }

    companion object {
        private const val TAG = "PinCodePresenter"
    }
}
