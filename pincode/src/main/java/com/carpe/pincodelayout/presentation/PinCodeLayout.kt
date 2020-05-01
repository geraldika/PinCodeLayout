package com.carpe.pincodelayout.presentation

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carpe.pincodelayout.R
import com.carpe.pincodelayout.color
import com.carpe.pincodelayout.model.Dot
import com.carpe.pincodelayout.model.PinCodeItem
import com.carpe.pincodelayout.presentation.base.BaseFrameLayout
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.layout_pin_code.view.*

abstract class PinCodeLayout<V : PinCodeView> : PinCodeView, BaseFrameLayout {

    abstract val presenter: PinCodePresenter<V>

    override val layoutResId: Int
        get() = R.layout.layout_pin_code

    private val dotsAdapter: DotsAdapter by lazy {
        DotsAdapter()
    }

    private val pinCodeAdapter: PinCodeAdapter by lazy {
        PinCodeAdapter(onItemClickListener = { subject.onNext(it) })
    }

    private val subject by lazy { PublishSubject.create<PinCodeItem>() }

    private var maxDigit = 0
    private var colorActive: Int = R.color.colorDigitPressedStroke
    private var colorInactive: Int = R.color.colorDigitNormalStroke

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

    override fun loadAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.PinCodeLayout, 0, 0)
            styledAttrs.getResourceId(
                R.styleable.PinCodeLayout_tintActive,
                R.color.colorDigitPressedStroke
            ).let { colorActive = it }

            styledAttrs.getResourceId(
                R.styleable.PinCodeLayout_tintInactive,
                R.color.colorDigitNormalStroke
            ).let { colorInactive = it }

            styledAttrs.getInteger(
                R.styleable.PinCodeLayout_maxDigits,
                4
            ).let { maxDigit = it }
            styledAttrs.recycle()
        }
    }

    override fun showDots(dots: List<Dot>) {
        dotsRecyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.HORIZONTAL
        }
        dotsAdapter.apply {
            dotsRecyclerView.adapter = this
            dotColorFilterActive = context.resources.color(R.color.colorAccent)
            dotColorFilterInactive = context.resources.color(R.color.colorPrimary)
            this.items = dots
        }
    }

    override fun showPinCodeItems(items: List<PinCodeItem>) {
        pinCodeRecyclerView.layoutManager = GridLayoutManager(context, 3)
        pinCodeAdapter.apply {
            pinCodeRecyclerView.adapter = this
            this.items = items
        }
    }

    override fun showActiveDot() {
        dotsAdapter.updateDot(true)
    }

    override fun showInactiveDot() {
        dotsAdapter.updateDot(false)
    }

    override fun showFingerPrintCheck() {

    }

    @SuppressLint("CheckResult")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        subject.subscribe { digit ->
            presenter.onNextDigit(digit)
        }
        presenter.initPinCodeView(maxDigit)
    }
}