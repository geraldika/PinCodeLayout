package com.carpe.pincodelayout.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.carpe.pincodelayout.R
import com.carpe.pincodelayout.drawable
import com.carpe.pincodelayout.model.PinCodeItem
import com.carpe.pincodelayout.model.PinCodeItem.Companion.TYPE_DIGIT
import com.carpe.pincodelayout.model.PinCodeItem.Companion.TYPE_ERASER
import kotlinx.android.synthetic.main.layout_digit_item.view.*
import kotlinx.android.synthetic.main.layout_erase_item.view.*
import kotlinx.android.synthetic.main.layout_finger_print_item.view.*

class PinCodeAdapter(
    @DrawableRes private var drawableDigitRes: Int,
    @DrawableRes private var drawableFingerPrintEnableRes: Int,
    @DrawableRes private var drawableFingerPrintDisableRes: Int,
    @DrawableRes private var drawableEraserRes: Int,
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
                drawableDigitRes
            )
            ITEM_TYPE_ERASER -> EraseViewHolder(
                inflateView(
                    parent,
                    R.layout.layout_erase_item
                ),
                onItemClickListener,
                drawableDigitRes,
                drawableEraserRes
            )
            else -> FingerPrintViewHolder(
                inflateView(
                    parent,
                    R.layout.layout_finger_print_item
                ),
                onItemClickListener,
                drawableDigitRes,
                drawableFingerPrintEnableRes,
                drawableFingerPrintDisableRes
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

        const val NO_COLOR_FILTER = -1
    }
}

abstract class BasePinCodeViewHolder(
    view: View,
    private val onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes open val drawableDigitRes: Int
) : RecyclerView.ViewHolder(view) {
    open fun bind(item: PinCodeItem) {
        itemView.setOnClickListener { onItemClickListener.invoke(item) }
    }
}

class DigitViewHolder(
    view: View,
    private val onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes override val drawableDigitRes: Int
) : BasePinCodeViewHolder(view, onItemClickListener, drawableDigitRes) {
    override fun bind(item: PinCodeItem) {
        super.bind(item)
        with(itemView) {
            digitButton.text = (item as PinCodeItem.Digit).digit.toString()
            digitButton.setOnClickListener { onItemClickListener.invoke(item) }
            digitButton.background = context.drawable(drawableDigitRes)
        }
    }
}

class EraseViewHolder(
    view: View,
    private val onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes override val drawableDigitRes: Int,
    @DrawableRes private var drawableEraserRes: Int
) : BasePinCodeViewHolder(view, onItemClickListener, drawableDigitRes) {
    override fun bind(item: PinCodeItem) {
        super.bind(item)
        with(itemView) {
            eraseImageView.background = context.drawable(drawableDigitRes)
            eraseImageView.setImageDrawable(context.drawable(drawableEraserRes))
            eraseImageView.setOnClickListener { onItemClickListener.invoke(item) }
        }
    }
}

class FingerPrintViewHolder(
    view: View,
    onItemClickListener: (PinCodeItem) -> (Unit),
    @DrawableRes override val drawableDigitRes: Int,
    @DrawableRes private var drawableFingerPrintEnableRes: Int,
    @DrawableRes private var drawableFingerPrintDisableRes: Int
) : BasePinCodeViewHolder(view, onItemClickListener, drawableDigitRes) {
    override fun bind(item: PinCodeItem) {
        super.bind(item)
        with(itemView) {
            fingerPrintImageView.background = context.drawable(drawableDigitRes)
            val drawableRes = if ((item as PinCodeItem.FingerPrint).enable) {
                drawableFingerPrintEnableRes
            } else {
                drawableFingerPrintDisableRes
            }
            fingerPrintImageView.setImageDrawable(context.drawable(drawableRes))
        }
    }
}