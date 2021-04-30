package com.example.notesapp.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.notesapp.R
import com.example.notesapp.ui.base.BaseActivity
import com.example.notesapp.ui.create.color_sheet.ColorBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_create_project.*
import kotlinx.android.synthetic.main.activity_project.*

class CreateProjectActivity : BaseActivity<CreateProjectViewModel>
    (R.layout.activity_create_project, CreateProjectViewModel::class.java) {
    override fun setupViews() {
        setupListeners()
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

}