package com.example.notesapp.ui.create

import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.notesapp.R
import com.example.notesapp.helper.showPerfectToast
import com.example.notesapp.ui.base.BaseActivity
import com.example.notesapp.ui.create.color_sheet.ColorBottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_create_project.*

class CreateProjectActivity : BaseActivity<CreateProjectViewModel>
    (R.layout.activity_create_project, CreateProjectViewModel::class) {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_create_project, menu)
        return true
    }

    override fun setupViews() {
        setupToolbar()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_add_project)
        supportActionBar?.title = resources.getString(R.string.all_projects)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_add_project.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupListeners() {
        btn_color.setOnClickListener {
            ColorBottomSheetDialogFragment().apply {
                show(supportFragmentManager, ColorBottomSheetDialogFragment.COLOR_BOTTOM_SHEET)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_create -> createProject()
        }
        return true
    }

    private fun createProject() {
        val projectName = et_add_project_title.text.toString()
        viewModel.createProject(projectName)
    }

    override fun subscribeToLiveData() {
        viewModel.createResult.observe(this, Observer {
            if (it == true) {
                showPerfectToast(this, "Проект успешно создан")
                finish()
            }
        })

        viewModel.message.observe(this, Observer {
            showPerfectToast(this, it)
        })
    }
}