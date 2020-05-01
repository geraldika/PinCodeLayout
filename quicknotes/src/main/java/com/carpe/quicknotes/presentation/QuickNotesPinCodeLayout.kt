package com.carpe.quicknotes.presentation

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.carpe.pincodelayout.presentation.PinCodeLayout
import com.carpe.quicknotes.di.Scopes
import toothpick.Scope
import toothpick.Toothpick

open class QuickNotesPinCodeLayout : PinCodeLayout<QuickNotesPincodeView>, QuickNotesPincodeView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private lateinit var scope: Scope

    @InjectPresenter
    override lateinit var presenter: QuickNotesPinCodePresenter

    @ProvidePresenter
    fun providePresenter(): QuickNotesPinCodePresenter {
        return scope.getInstance(QuickNotesPinCodePresenter::class.java)
    }

    override fun initPresenterInstance() {
        scope = Toothpick.openScopes(Scopes.Application)
    }

    override fun showPinCodeSuccessfullySaved() {
        TODO("Not yet implemented")
    }

    override fun showErrorWhilePinCode() {
        TODO("Not yet implemented")
    }
}