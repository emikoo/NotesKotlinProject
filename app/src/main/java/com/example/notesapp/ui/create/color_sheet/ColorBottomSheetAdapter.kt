package com.example.notesapp.ui.create.color_sheet

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.data.model.PrimaryColor
import com.example.notesapp.data.model.Project
import com.example.notesapp.extentions.setCornerRadius
import com.example.notesapp.helper.ColorType
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
            listener.onItemClick(color)
        }
    }

    fun addItems(items: MutableList<PrimaryColor>){
        colorArray = items
        notifyDataSetChanged()
    }
}

class ColorBottomSheetViewHolder(itemView: View): BaseViewHolder(itemView){
    fun bind(item: PrimaryColor) {
        val radius = itemView.context.resources.getDimension(R.dimen.dp_25)

        itemView.view_color.setBackgroundColor(ColorType.getProjectColorType(item.id))

        itemView.view_color.setCornerRadius(
            topLeft = radius,
            topRight = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
    }
}

interface ClickListener {
    fun onItemClick(item: PrimaryColor)
}