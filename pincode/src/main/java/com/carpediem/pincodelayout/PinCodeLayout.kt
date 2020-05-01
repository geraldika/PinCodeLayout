package com.carpediem.pincodelayout

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import com.carpediem.pincodelayout.model.PinCodeItem
import com.carpediem.pincodelayout.presentation.base.BaseFrameLayout
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.layout_pin_code_panel.view.*

//todo only portrait orientation
//todo add fingerprint
//todo clear
//todo time interval

abstract class PinCodeLayout<V : PinCodeView> : BaseFrameLayout, PinCodeView {

    abstract val presenter: PinCodePresenter<V>

    override val layoutResId: Int
        get() = R.layout.layout_pin_code_panel

    private val pinCodeAdapter: PinCodeAdapter by lazy {
        PinCodeAdapter(onItemClickListener = { subject::onNext })
    }

    private val subject: PublishSubject<Int> = PublishSubject.create()

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

    override fun showPinCodeItems(items: List<PinCodeItem>) {
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        pinCodeAdapter.apply {
            recyclerView.adapter = this
            pinCodeAdapter.items = items
        }
    }

    @SuppressLint("CheckResult")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        subject.subscribe { digit ->
            presenter.onNextDigit(digit)
        }
        presenter.initDigits()
    }
}