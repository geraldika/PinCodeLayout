package com.carpe.quicknotes.extensions

import com.carpediem.dailynotes.BuildConfig

fun isDebug() = BuildConfig.DEBUG
fun isProduction() = !BuildConfig.DEBUG//BuildConfig.RELEASE