package com.carpe.quicknotes.presentation.pincode

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.carpe.pincodelayout.presentation.PinCodeLayout
import com.carpe.quicknotes.di.Scopes
import toothpick.Scope
import toothpick.Toothpick

open class QuickNotesPinCodeLayout : PinCodeLayout<QuickNotesPincodeView>,
    QuickNotesPincodeView {

    override val activity: Activity
        get() = (context as Activity)

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

    override fun showErrorWhilePinCodeValidation() {

    }

    override fun showSuccessPinCodeCheck() {

    }

    override fun showSuccessPinCodeSave() {

    }
}