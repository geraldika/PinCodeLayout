package com.carpe.quicknotes.presentation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.carpe.pincodelayout.presentation.PinCodeView

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuickNotesPincodeView :
    PinCodeView {
}