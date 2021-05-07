package com.example.notesapp.ui.create.color_sheet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.data.model.PrimaryColor
import com.example.notesapp.extentions.setCornerRadius
import com.example.notesapp.helper.ColorType
import com.example.notesapp.helper.ColorType.colors
import com.example.notesapp.helper.showToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_create_project.*
import kotlinx.android.synthetic.main.layout_color_bottom_sheet.*

class ColorBottomSheetDialogFragment: BottomSheetDialogFragment(), ClickListener {

    companion object {
        const val COLOR_BOTTOM_SHEET = "CustomBottomSheetDialogFragment"
    }

    lateinit var adapter: ColorBottomSheetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_color_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        toolbar_color.setNavigationOnClickListener { this.dismiss() }
    }

    private fun setupRecyclerView() {
        adapter = ColorBottomSheetAdapter(this)
        list_color.layoutManager = GridLayoutManager(requireContext(), 6)
        list_color.adapter = adapter

        adapter.addItems(colors)
    }

    override fun onItemClick(item: PrimaryColor) {
        showToast(requireContext(), item.colorName)
        this.dismiss()
    }
}