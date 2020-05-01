package com.carpe.pincodelayout.model

sealed class PinCodeItem(val type: Int) {

    class Digit(val digit: Int) : PinCodeItem(TYPE_DIGIT)
    class Eraser : PinCodeItem(TYPE_ERASER)
    class FingerPrint : PinCodeItem(TYPE_FINGER_PRINT)

    companion object {
        const val TYPE_DIGIT = 0
        const val TYPE_ERASER = 1
        const val TYPE_FINGER_PRINT = 2
    }
}