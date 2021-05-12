package com.example.notesapp.ui.projects

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.model.Project
import com.example.notesapp.helper.ItemSimpleTouch
import com.example.notesapp.ui.base.BaseActivity
import com.example.notesapp.ui.create.CreateProjectActivity
import com.example.notesapp.ui.tasks.TaskListActivity
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : BaseActivity<ProjectViewModel>(
    R.layout.activity_project, ProjectViewModel::class), ClickListener {

    lateinit var adapter: ProjectsAdapter

    override fun onResume() {
        super.onResume()
        viewModel.fetchProjects()
    }

    override fun setupViews() {
        setupRecyclerView()
        setupSearchView()
        setupListeners()
        deleteSwipeAction()
    }

    private fun setupRecyclerView() {
        adapter = ProjectsAdapter(this)
        list_projects.layoutManager = LinearLayoutManager(this)
        list_projects.adapter = adapter
    }

    private fun setupSearchView() {
        search_view.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Handler().postDelayed(Runnable {
                    if (newText == "") {
                        adapter.addItems(viewModel.project)
                    } else {

                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<Project>()
                        viewModel.project.forEach {
                            if (it.name?.toLowerCase()?.contains(searchText)!!) filtered.add(it)
                        }
                        adapter.addItems(filtered)
                    }
                }, 800)
                return false
            }
        }
        )
    }

    private fun deleteSwipeAction() {
        val swipeHandler = object : ItemSimpleTouch(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = viewModel.data.value?.get(position)
                adapter.deleteItem(position)
                viewModel.deleteProject(item?.id)

//                showActionSnackbar(list_projects, "Вы удалили $name", "Востановить",
//                    { restoreDeletedItem(item, position) }, this
//                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(list_projects)
    }

    private fun restoreDeletedItem(item: Project?, position: Int) {
//        viewModel.restoreProduct(item)
//        adapter.restoreItem(item!!, position)
    }

    private fun setupListeners() {
        btn_add_project.setOnClickListener {
            val intent = Intent(this, CreateProjectActivity::class.java)
            startActivity(intent)
        }
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(this, Observer {
            if (it != null) adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Project) {
        TaskListActivity.instance(this, item)
    }
}