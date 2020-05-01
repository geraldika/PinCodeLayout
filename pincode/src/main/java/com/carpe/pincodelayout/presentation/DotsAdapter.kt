package com.carpe.pincodelayout.presentation

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.carpe.pincodelayout.R
import com.carpe.pincodelayout.drawable
import com.carpe.pincodelayout.model.Dot
import kotlinx.android.synthetic.main.layout_dot_item.view.*

class DotsAdapter : RecyclerView.Adapter<DotViewHolder>() {

    var items = listOf<Dot>()
        set(value) {
            if (field != value) {
                field = value

                notifyDataSetChanged()
            }
        }

    @ColorInt
    var dotColorFilterInactive: Int = PinCodeAdapter.NO_COLOR_FILTER

    @ColorInt
    var dotColorFilterActive: Int = PinCodeAdapter.NO_COLOR_FILTER

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        return DotViewHolder(
            inflateView(parent, R.layout.layout_dot_item),
            dotColorFilterInactive,
            dotColorFilterActive
        )
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateDot(isActive: Boolean) {
        val position =
            if (isActive) items.indexOfFirst { it.isActive } else items.indexOfLast { !it.isActive }
        if (position != RecyclerView.NO_POSITION) notifyItemChanged(position)
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutResId: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    }
}

class DotViewHolder(
    view: View,
    @ColorInt val dotColorFilterInactive: Int,
    @ColorInt val dotColorFilterActive: Int
) : RecyclerView.ViewHolder(view) {
    fun bind(item: Dot) {
        item.isActive = !item.isActive
        with(itemView.dotImageView) {
            if (item.isActive) {
                background = context.drawable(R.drawable.background_pin_code_element_inactive)
                //if (dotColorFilterActive != NO_COLOR_FILTER) backgroundTintList =
                // ColorStateList.valueOf(dotColorFilterActive)

            } else if (!item.isActive) {
                background = context.drawable(R.drawable.background_pin_code_element_active)
                //  if (dotColorFilterActive != NO_COLOR_FILTER) backgroundTintList =
                //    ColorStateList.valueOf(dotColorFilterInactive)
            }
        }
    }

    companion object {
        const val NO_COLOR_FILTER = -1
    }
}