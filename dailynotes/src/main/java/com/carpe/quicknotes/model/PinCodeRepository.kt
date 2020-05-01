package com.carpe.quicknotes.model

import javax.inject.Inject

class PinCodeRepository @Inject constructor(
    private val pincodeLocalDataSource: PincodeLocalDataSource
) {

}