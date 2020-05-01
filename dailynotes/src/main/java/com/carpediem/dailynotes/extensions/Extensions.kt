package com.carpediem.dailynotes.extensions

import com.carpediem.dailynotes.BuildConfig

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"

fun isDebug() = BuildConfig.DEBUG
fun isProduction() = BuildConfig.PROD