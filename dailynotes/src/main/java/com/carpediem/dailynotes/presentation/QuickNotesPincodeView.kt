package com.carpediem.dailynotes.presentation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.carpediem.pincodelayout.PinCodeView

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuickNotesPincodeView : PinCodeView {
}