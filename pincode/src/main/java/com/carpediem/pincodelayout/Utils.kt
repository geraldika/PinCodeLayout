package com.carpediem.pincodelayout

import com.carpediem.pincodelayout.model.PinCodeItem

object Utils {

    fun generatePinCodeItems(): List<PinCodeItem> = listOf(
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    ).map {
        PinCodeItem.Digit(it) as PinCodeItem
    }.toMutableList()
        .apply {
            add(9, PinCodeItem.FingerPrint())
            add(11, PinCodeItem.Eraser())
        }
}