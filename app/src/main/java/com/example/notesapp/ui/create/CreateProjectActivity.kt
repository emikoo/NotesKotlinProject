package com.example.notesapp.ui.create

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.notesapp.R
import com.example.notesapp.data.model.PrimaryColor
import com.example.notesapp.helper.showPerfectToast
import com.example.notesapp.helper.visible
import com.example.notesapp.ui.base.BaseActivity
import com.example.notesapp.ui.create.color_sheet.ColorBottomSheetDialogFragment
import com.example.notesapp.ui.create.color_sheet.PickerColor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_create_project.*
import kotlinx.android.synthetic.main.item_color.*

class CreateProjectActivity : BaseActivity<CreateProjectViewModel>
    (R.layout.activity_create_project, CreateProjectViewModel::class), PickerColor {

    var selectedColor: PrimaryColor? = null

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
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                ColorBottomSheetDialogFragment(this)
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(
                supportFragmentManager,
                bottomSheetDialogFragment.tag
            )
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
        viewModel.createProject(projectName, selectedColor?.id)    }

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

    override fun chosenColor(colors: MutableList<PrimaryColor>) {
        colors.forEach { if (it.selected) setupSelectedViews(it) }
    }

    private fun setupSelectedViews(item: PrimaryColor) {
        selectedColor = selectedColor
        view_color.visible()
        view_color.background.setColorFilter(Color.parseColor(item.hexNumber), PorterDuff.Mode.SRC_ATOP)
        btn_color.text = "Change color"
    }
}