package com.example.notesapp.ui.create.color_sheet

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.data.model.PrimaryColor
import com.example.notesapp.data.model.Project
import com.example.notesapp.extentions.setCornerRadius
import com.example.notesapp.helper.ColorType
import com.example.notesapp.helper.gone
import com.example.notesapp.helper.visible
import com.example.notesapp.ui.base.BaseAdapter
import com.example.notesapp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_color.view.*
import kotlinx.android.synthetic.main.item_projects.view.*

class ColorBottomSheetAdapter(private val listener: ClickListener): BaseAdapter() {

    var colorArray = mutableListOf<PrimaryColor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ColorBottomSheetViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return colorArray.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        setupColorBottomSheetViewHolder(holder as ColorBottomSheetViewHolder, position)
    }

    private fun setupColorBottomSheetViewHolder(holder: ColorBottomSheetViewHolder, position: Int){
        val color = colorArray[position]
        holder.bind(color)
        holder.itemView.setOnClickListener {
            listener.onItemClick(color, position)
        }
    }

    fun addItems(items: MutableList<PrimaryColor>){
        colorArray = items
        notifyDataSetChanged()
    }
}

class ColorBottomSheetViewHolder(itemView: View): BaseViewHolder(itemView){
    fun bind(item: PrimaryColor) {
        if (item.selected) itemView.selected_color_view.visible()
        else itemView.selected_color_view.gone()
        itemView.color_view.background.setColorFilter(Color.parseColor(item.hexNumber), PorterDuff.Mode.SRC_ATOP)
    }
}
interface ClickListener {
    fun onItemClick(item: PrimaryColor, position: Int)
}