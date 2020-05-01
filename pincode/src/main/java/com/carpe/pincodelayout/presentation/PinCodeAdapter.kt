package com.carpe.pincodelayout.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.carpe.pincodelayout.R
import com.carpe.pincodelayout.color
import com.carpe.pincodelayout.drawable
import com.carpe.pincodelayout.model.PinCodeItem
import com.carpe.pincodelayout.model.PinCodeItem.Companion.TYPE_DIGIT
import com.carpe.pincodelayout.model.PinCodeItem.Companion.TYPE_ERASER
import kotlinx.android.synthetic.main.layout_digit_item.view.*
import kotlinx.android.synthetic.main.layout_erase_item.view.*
import kotlinx.android.synthetic.main.layout_finger_print_item.view.*

class PinCodeAdapter(
    @DrawableRes private var drawableDigitRes: Int,
    @ColorRes private var colorDigitTintRes: Int,
    @DrawableRes private var iconFingerPrintEnableRes: Int,
    @DrawableRes private var iconFingerPrintDisableRes: Int,
    @DrawableRes private var iconEraserRes: Int,
    @DrawableRes private var drawableFingerPrintRes: Int,
    @ColorRes private var colorFingerPrintTintRes: Int,
    private val onItemClickListener: ((PinCodeItem) -> (Unit))
) : RecyclerView.Adapter<BasePinCodeViewHolder>() {

    var items = emptyList<PinCodeItem>()
        set(value) {
            if (field != value) {
                field = value

                notifyDataSetChanged()
            }
        }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item.type) {
            TYPE_DIGIT -> ITEM_TYPE_DIGIT
            TYPE_ERASER -> ITEM_TYPE_ERASER
            else -> ITEM_TYPE_FINGER_PRINT
        }
    }

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePinCodeViewHolder {
        return when (viewType) {
            ITEM_TYPE_DIGIT -> DigitViewHolder(
                inflateView(
                    parent,
                    R.layout.layout_digit_item
                ),
                onItemClickListener,
                drawableDigitRes,
                colorDigitTintRes
            )

            ITEM_TYPE_ERASER -> EraseViewHolder(
                inflateView(
                    parent,
                    R.layout.layout_erase_item
                ),
                onItemClickListener,
                drawableDigitRes,
                drawableDigitRes,
                iconEraserRes,
                colorDigitTintRes
            )
            else -> FingerPrintViewHolder(
                inflateView(
                    parent,
                    R.layout.layout_finger_print_item
                ),
                onItemClickListener,
                drawableDigitRes,
                iconFingerPrintEnableRes,
                iconFingerPrintDisableRes,
                colorDigitTintRes,
                drawableFingerPrintRes,
                colorFingerPrintTintRes
            )
        }
    }

    override fun onBindViewHolder(holder: BasePinCodeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutResId: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    }

    companion object {
        const val ITEM_TYPE_DIGIT = 0
        const val ITEM_TYPE_ERASER = 1
        const val ITEM_TYPE_FINGER_PRINT = 2
    }
}

abstract class BasePinCodeViewHolder(
    view: View,
    private val onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes open val drawableDigitRes: Int,
    @ColorRes private var colorDigitTintRes: Int
) : RecyclerView.ViewHolder(view) {
    open fun bind(item: PinCodeItem) {
        itemView.setOnClickListener { onItemClickListener.invoke(item) }
    }
}

class DigitViewHolder(
    view: View,
    private val onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes override val drawableDigitRes: Int,
    @ColorRes private var colorDigitTintRes: Int
) : BasePinCodeViewHolder(view, onItemClickListener, drawableDigitRes, colorDigitTintRes) {
    override fun bind(item: PinCodeItem) {
        super.bind(item)
        with(itemView) {
            digitButton.text = (item as PinCodeItem.Digit).digit.toString()
            digitButton.setOnClickListener { onItemClickListener.invoke(item) }
            digitButton.setTextColor(context.resources.color(colorDigitTintRes))
            digitButton.background = context.drawable(drawableDigitRes)
        }
    }
}

class EraseViewHolder(
    view: View,
    private val onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes override val drawableDigitRes: Int,
    @DrawableRes private var drawableEraserRes: Int,
    @DrawableRes private var iconEraserRes: Int,
    @ColorRes private var colorDigitTintRes: Int
) : BasePinCodeViewHolder(view, onItemClickListener, drawableDigitRes, colorDigitTintRes) {
    override fun bind(item: PinCodeItem) {
        super.bind(item)
        with(itemView) {
            // eraseImageView.background = context.drawable(iconEraserRes)
            eraseImageView.setImageDrawable(context.drawable(iconEraserRes))
            eraseImageView.setColorFilter(context.resources.color(colorDigitTintRes))
            eraseImageView.setOnClickListener { onItemClickListener.invoke(item) }
        }
    }
}

class FingerPrintViewHolder(
    view: View,
    onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes override val drawableDigitRes: Int,
    @DrawableRes private var iconFingerPrintEnableRes: Int,
    @DrawableRes private var iconFingerPrintDisableRes: Int,
    @ColorRes private var colorDigitTintRes: Int,
    @DrawableRes private var drawableFingerPrintRes: Int,
    @ColorRes private var colorFingerPrintTintRes: Int
) : BasePinCodeViewHolder(view, onItemClickListener, drawableDigitRes, colorDigitTintRes) {
    override fun bind(item: PinCodeItem) {
        super.bind(item)
        with(itemView) {
            fingerPrintImageView.background = context.drawable(drawableFingerPrintRes)
            val drawableRes = if ((item as PinCodeItem.FingerPrint).enable) {
                iconFingerPrintEnableRes
            } else {
                iconFingerPrintDisableRes
            }
            fingerPrintImageView.setImageDrawable(context.drawable(drawableRes))
            fingerPrintImageView.setColorFilter(context.resources.color(colorFingerPrintTintRes))
        }
    }
}