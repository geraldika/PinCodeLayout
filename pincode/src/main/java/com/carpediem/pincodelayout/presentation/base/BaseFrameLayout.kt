package com.carpediem.pincodelayout.presentation.base

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.arellomobile.mvp.MvpDelegate

abstract class BaseFrameLayout : FrameLayout {

    abstract val layoutResId: Int

    private var isCreated: Boolean = false
    private val mvpDelegate: MvpDelegate<out BaseFrameLayout> by lazy { MvpDelegate(this) }

    constructor(context: Context) : super(context) {
        initLayout()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initLayout()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initLayout()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initLayout()
    }

    @SuppressLint("CheckResult")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initMvpDelegate()
        mvpDelegate.onAttach()
    }

    override fun onDetachedFromWindow() {
        mvpDelegate.onSaveInstanceState()
        mvpDelegate.onDetach()
        super.onDetachedFromWindow()
    }

    open fun initLayout() {
        View.inflate(context, layoutResId, this)
        initMvpDelegate()
    }

    private fun initMvpDelegate() {
        if (!isCreated) {
            mvpDelegate.onCreate()
            isCreated = true
        }
    }
}