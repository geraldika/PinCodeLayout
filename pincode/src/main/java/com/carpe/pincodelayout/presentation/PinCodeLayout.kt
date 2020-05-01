package com.carpe.pincodelayout.presentation

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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


    abstract val presenter: PinCodePresenter<V>

    override val layoutResId: Int
        get() = R.layout.layout_pin_code

    @ColorRes
    private var backgroundColorRes: Int = 0 //R.color.colorPrimary

    @DrawableRes
    private var drawableDotInactiveRes: Int = 0 //R.drawable.background_pin_code_element_inactive

    @DrawableRes
    private var drawableDotActiveRes: Int = 0 // R.drawable.background_pin_code_element_active

    @DrawableRes
    private var drawableDigitRes = 0//R.drawable.background_digit_button

    @DrawableRes
    private var drawableFingerPrintEnableRes: Int = 0 //R.drawable.ic_fingerprint_black_24dp

    @DrawableRes
    private var drawableFingerPrintDisableRes: Int = 0 //R.drawable.ic_fingerprint_black_24dp

    @DrawableRes
    private var drawableEraserRes: Int = 0 //R.drawable.ic_backspace_black_24dp

    @StringRes
    private var createPinCodeText: Int = 0 //R.string.create_pin_code

    private var maxDigit = 0

    private val dotsAdapter: DotsAdapter by lazy {
        DotsAdapter(
            drawableDotActiveRes = drawableDotActiveRes,
            drawableDotInactiveRes = drawableDotInactiveRes
        )
    }

    private val pinCodeAdapter: PinCodeAdapter by lazy {
        PinCodeAdapter(
            drawableDigitRes = drawableDigitRes,
            drawableFingerPrintEnableRes = drawableFingerPrintEnableRes,
            drawableFingerPrintDisableRes = drawableFingerPrintDisableRes,
            drawableEraserRes = drawableEraserRes,
            onItemClickListener = { subject.onNext(it) }
        )
    }

    private val subject by lazy { PublishSubject.create<PinCodeItem>() }

    override fun loadAttributes(attrs: AttributeSet?) {
        attrs?.let { setAttributes(it) }
    }

    override fun showDots(dots: List<Dot>) {
        dotsRecyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.HORIZONTAL
        }
        dotsAdapter.apply {
            dotsRecyclerView.adapter = this
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

    override fun inflateLayout() {
        super.inflateLayout()
        pinCodeContainer.setBackgroundColor(context.resources.color(backgroundColorRes))
    }

    @SuppressLint("CheckResult")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        subject.subscribe { digit ->
            presenter.onNextDigit(digit)
        }
        presenter.initPinCodeView(maxDigit)
    }

    private fun setAttributes(attrs: AttributeSet?) {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.PinCodeLayout, 0, 0)
        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_backgroundColor,
            0
        ).let { backgroundColorRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_drawableDotInactive,
            0
        ).let { drawableDotInactiveRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_drawableDotActive,
            0
        ).let { drawableDotActiveRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_drawableDigit,
            0
        ).let {
            drawableDigitRes = it
        }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_drawableFingerPrintDisable,
            0
        ).let { drawableFingerPrintDisableRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_drawableFingerPrintEnable,
            0
        ).let { drawableFingerPrintEnableRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_drawableEraser,
            0
        ).let { drawableEraserRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_createPinCodeText,
            0
        ).let { createPinCodeText = it }

        styledAttrs.getInteger(
            R.styleable.PinCodeLayout_maxDigits,
            0
        ).let { maxDigit = it }
        styledAttrs.recycle()
    }
}