package com.carpediem.pincodelayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.carpediem.pincodelayout.model.PinCodeItem
import com.carpediem.pincodelayout.model.PinCodeItem.Companion.TYPE_DIGIT
import com.carpediem.pincodelayout.model.PinCodeItem.Companion.TYPE_ERASER
import kotlinx.android.synthetic.main.layout_digit_item.view.*
import kotlinx.android.synthetic.main.layout_erase_item.view.*
import kotlinx.android.synthetic.main.layout_finger_print_item.view.*

class PinCodeAdapter(
    private val onItemClickListener: ((PinCodeItem) -> (Unit))
) : RecyclerView.Adapter<BasePinCodeViewHolder>() {

    var items = emptyList<PinCodeItem>()
        set(value) {
            if (field != value) {
                field = value

                notifyDataSetChanged()
            }
        }

    @ColorInt
    var colorFilter: Int = -1

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
                inflateView(parent, R.layout.layout_digit_item),
                onItemClickListener,
                colorFilter
            )
            ITEM_TYPE_ERASER -> EraseViewHolder(
                inflateView(parent, R.layout.layout_erase_item),
                onItemClickListener,
                colorFilter
            )
            else -> FingerPrintViewHolder(
                inflateView(parent, R.layout.layout_finger_print_item),
                onItemClickListener,
                colorFilter
            )
        }
    }

    override fun onBindViewHolder(holderBase: BasePinCodeViewHolder, position: Int) {
        holderBase.bind(items[position])
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
    @ColorInt val colorFilter: Int
) : RecyclerView.ViewHolder(view) {
    open fun bind(item: PinCodeItem) {
        itemView.setOnClickListener { onItemClickListener.invoke(item) }
    }
}

class DigitViewHolder(
    view: View,
    onItemClickListener: (PinCodeItem) -> (Unit),
    @ColorInt colorFilter: Int
) : BasePinCodeViewHolder(view, onItemClickListener, colorFilter) {
    override fun bind(item: PinCodeItem) {
        with(itemView) {
            digitButton.text = (item as PinCodeItem.Digit).digit.toString()
            if (colorFilter != -1) digitButton.setTextColor(colorFilter)
        }
    }
}

class EraseViewHolder(
    view: View,
    onItemClickListener: (PinCodeItem) -> (Unit),
    @ColorInt colorFilter: Int
) : BasePinCodeViewHolder(view, onItemClickListener, colorFilter) {
    override fun bind(item: PinCodeItem) {
        if (colorFilter != -1) itemView.eraseImageView.setColorFilter(colorFilter)
    }
}

class FingerPrintViewHolder(
    view: View,
    onItemClickListener: (PinCodeItem) -> (Unit),
    @ColorInt colorFilter: Int
) : BasePinCodeViewHolder(view, onItemClickListener, colorFilter) {
    override fun bind(item: PinCodeItem) {
        if (colorFilter != -1) itemView.fingerPrintImageView.setColorFilter(colorFilter)
    }
}