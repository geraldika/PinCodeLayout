package com.carpediem.dailynotes.model

import javax.inject.Inject

class PinCodeRepository @Inject constructor(
    private val pincodeLocalDataSource: PincodeLocalDataSource
) {

}