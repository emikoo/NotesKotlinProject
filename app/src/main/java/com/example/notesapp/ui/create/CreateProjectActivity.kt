package com.example.notesapp.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.notesapp.R
import com.example.notesapp.data.model.PrimaryColor
import com.example.notesapp.extentions.setCornerRadius
import com.example.notesapp.helper.ColorType
import com.example.notesapp.ui.base.BaseActivity
import com.example.notesapp.ui.create.color_sheet.ClickListener
import com.example.notesapp.ui.create.color_sheet.ColorBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_create_project.*
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.item_color.view.*

class CreateProjectActivity : BaseActivity<CreateProjectViewModel>
    (R.layout.activity_create_project, CreateProjectViewModel::class.java), ClickListener {

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
            R.id.action_create -> {

            }
        }
        return true
    }

    override fun subscribeToLiveData() {

    }

    override fun onItemClick(item: PrimaryColor) {

    }

}