package com.example.notesapp.ui.projects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.model.Project
import com.example.notesapp.helper.ColorType
import com.example.notesapp.ui.base.BaseAdapter
import com.example.notesapp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_empty.view.*
import kotlinx.android.synthetic.main.item_projects.view.*

class ProjectsAdapter: BaseAdapter() {

    var projectArray = mutableListOf<Project>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if(viewType == VIEW_TYPE_DATA) ProjectsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_projects, parent, false)
        ) else EmptyListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (projectArray.count() == 0) 1
        else projectArray.count()
    }

    override fun getItemViewType(position: Int): Int {
        return if (projectArray.count() == 0) VIEW_TYPE_EMPTY
        else VIEW_TYPE_DATA
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == VIEW_TYPE_DATA) setupProjectsViewHolder(holder as ProjectsViewHolder, position)
        else setupEmptyViewHolder(holder as EmptyListViewHolder, position)
    }

    private fun setupProjectsViewHolder(holder: ProjectsViewHolder, position: Int) {
        val item = projectArray[position]
        holder.bind(item)
    }

    private fun setupEmptyViewHolder(holder: EmptyListViewHolder, position: Int) {
        holder.bind()
    }

    fun addItems(items: MutableList<Project>) {
        projectArray = items
        notifyDataSetChanged()
    }

    companion object{
        val VIEW_TYPE_EMPTY = 1
        val VIEW_TYPE_DATA = 2
    }
}

class EmptyListViewHolder(itemView: View): BaseViewHolder(itemView){
    fun bind() {
        itemView.title_empty_tv.text = "НЕТ ПРОЕКТОВ"
        itemView.empty_iv.setImageResource(R.drawable.ic_empty)
    }
}

class ProjectsViewHolder(itemView: View): BaseViewHolder(itemView){
    fun bind(item: Project) {
        itemView.view_project_indicator.setBackgroundColor(ColorType.getProjectColorType(item.color))
        itemView.tv_title.text = item.name
    }
}