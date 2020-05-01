package com.carpe.pincodelayout.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.carpe.pincodelayout.R
import com.carpe.pincodelayout.drawable
import com.carpe.pincodelayout.model.Dot
import kotlinx.android.synthetic.main.layout_dot_item.view.*

class DotsAdapter(
    @DrawableRes private var drawableDotInactiveRes: Int,
    @DrawableRes private var drawableDotActiveRes: Int
) : RecyclerView.Adapter<DotViewHolder>() {

    var items = listOf<Dot>()
        set(value) {
            if (field != value) {
                field = value

                notifyDataSetChanged()
            }
        }

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        return DotViewHolder(
            inflateView(parent, R.layout.layout_dot_item),
            drawableDotInactiveRes,
            drawableDotActiveRes
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
    @DrawableRes private var drawableDotInactiveRes: Int,
    @DrawableRes private var drawableDotActiveRes: Int
) : RecyclerView.ViewHolder(view) {
    fun bind(item: Dot) {
        item.isActive = !item.isActive
        with(itemView.dotImageView) {
            background =
                context.drawable(if (item.isActive) drawableDotActiveRes else drawableDotInactiveRes)
        }
    }
}
