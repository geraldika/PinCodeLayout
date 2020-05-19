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
    protected open var isPinCodeExist = false

    @SuppressLint("CheckResult")
    open fun initPinCodeView(pinCodeDigitsSize: Int) {
        this.pinCodeDigitsSize = pinCodeDigitsSize
        val items = Utils.generatePinCodeItems()
        val dotsList = List<Dot>(pinCodeDigitsSize) { Dot() }
        viewState.showDots(dotsList)
        viewState.showPinCodeItems(items)

        pinCodeInteractor
            .isPinCodeExist()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ isExist ->
                isPinCodeExist = isExist
                if (isExist) {
                    viewState.showEnterPinCodeScreen()
                } else {
                    viewState.showCreatePinCodeScreen()
                }

            }, { error ->
                Log.e(TAG, "Error while pin code check", error)
            })
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
            pinCodeDigits.removeAt(pinCodeDigits.last() - 1)
            viewState.showInactiveDot()
        }
    }

    protected open fun validatePinCode(digit: Int) {
        viewState.showActiveDot()
        pinCodeDigits.add(digit)
        if (pinCodeDigits.size == pinCodeDigitsSize) {
            val pinCode = Utils.generatePinCodeString(pinCodeDigits)
            if (isPinCodeExist) checkPinCode(pinCode)
            else savePinCode(pinCode)
        }
    }

    @SuppressLint("CheckResult")
    protected open fun savePinCode(pinCode: String) {
        pinCodeInteractor
            .createPinCode(Utils.generatePinCodeString(pinCodeDigits))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showSuccessPinCodeSave()
            }, { error ->
                Log.e(TAG, "Error while creating pin code", error)
            })
    }

    @SuppressLint("CheckResult")
    protected open fun checkPinCode(pinCode: String) {
        Utils.generatePinCodeString(pinCodeDigits)
        pinCodeInteractor
            .checkPinCode(Utils.generatePinCodeString(pinCodeDigits))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showSuccessPinCodeCheck()
            }, { error ->
                Log.e(TAG, "Error while checking pin code", error)
            })
    }

    companion object {
        private const val TAG = "PinCodePresenter"
    }
}
