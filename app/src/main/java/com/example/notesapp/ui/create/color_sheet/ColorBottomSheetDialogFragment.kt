package com.example.notesapp.ui.create.color_sheet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_color_bottom_sheet.*

class ColorBottomSheetDialogFragment: BottomSheetDialogFragment() {

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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ColorBottomSheetAdapter()
        list_color.layoutManager = GridLayoutManager(requireContext(), 6)
        list_color.adapter = adapter
    }
}