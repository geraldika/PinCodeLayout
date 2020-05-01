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
    private var backgroundColorRes: Int = 0

    @DrawableRes
    private var drawableDotInactiveRes: Int = 0

    @DrawableRes
    private var drawableDotActiveRes: Int = 0

    @ColorRes
    private var colorDigitTintRes = 0

    @DrawableRes
    private var drawableDigitRes = 0

    @ColorRes
    private var colorFingerPrintTintRes = 0

    @DrawableRes
    private var backgroundFingerPrintRes: Int = 0

    @DrawableRes
    private var iconFingerPrintEnableRes: Int = 0

    @DrawableRes
    private var iconFingerPrintDisableRes: Int = 0

    @DrawableRes
    private var iconEraserRes: Int = 0

    @StringRes
    private var titleRes: Int = 0

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
            colorDigitTintRes = colorDigitTintRes,
            iconFingerPrintEnableRes = iconFingerPrintEnableRes,
            iconFingerPrintDisableRes = iconFingerPrintDisableRes,
            iconEraserRes = iconEraserRes,
            drawableFingerPrintRes = backgroundFingerPrintRes,
            colorFingerPrintTintRes = colorFingerPrintTintRes,
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
        titleTextView.setTextColor(context.resources.color(colorDigitTintRes))
        titleTextView.text = context.getString(titleRes)
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
            R.styleable.PinCodeLayout_colorDigitTint,
            0
        ).let {
            colorDigitTintRes = it
        }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_drawableFingerPrint,
            0
        ).let { backgroundFingerPrintRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_iconFingerPrintDisable,
            0
        ).let { iconFingerPrintDisableRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_colorFingerPrintTint,
            0
        ).let { colorFingerPrintTintRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_iconFingerPrintEnable,
            0
        ).let { iconFingerPrintEnableRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_iconEraser,
            0
        ).let { iconEraserRes = it }

        styledAttrs.getResourceId(
            R.styleable.PinCodeLayout_titleText,
            0
        ).let { titleRes = it }

        styledAttrs.getInteger(
            R.styleable.PinCodeLayout_maxDigits,
            0
        ).let { maxDigit = it }
        styledAttrs.recycle()
    }
}