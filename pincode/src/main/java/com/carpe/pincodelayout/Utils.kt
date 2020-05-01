package com.carpe.pincodelayout

import com.carpe.pincodelayout.model.PinCodeItem

object Utils {

    fun generatePinCodeItems(): List<PinCodeItem> = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 0
    ).map {
        PinCodeItem.Digit(it) as PinCodeItem
    }.toMutableList()
        .apply {
            add(9, PinCodeItem.FingerPrint(true))
            add(11, PinCodeItem.Eraser())
        }
}