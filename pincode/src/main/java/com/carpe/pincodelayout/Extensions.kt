package com.carpe.pincodelayout

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Resources.color(@ColorRes colorRes: Int) =
    if (Build.VERSION.SDK_INT >= 23) {
        getColor(colorRes, null)
    } else {
        @Suppress("DEPRECATION")
        getColor(colorRes)
    }

fun Context.drawable(@DrawableRes drawableRes: Int): Drawable {
    return ContextCompat.getDrawable(this, drawableRes)
        ?: throw IllegalArgumentException("drawable can't be null")
}