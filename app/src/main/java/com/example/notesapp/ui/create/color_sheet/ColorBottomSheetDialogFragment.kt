package com.example.notesapp.ui.create.color_sheet

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.data.model.PrimaryColor
import com.example.notesapp.helper.ColorType.colors
import com.example.notesapp.helper.showPerfectToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_color_bottom_sheet.*

interface PickerColor {
    fun chosenColor(colors: MutableList<PrimaryColor>)
}

class ColorBottomSheetDialogFragment(private val listener: PickerColor) : BottomSheetDialogFragment(), ClickListener {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_color -> dismiss()
        }
        return true
    }

    private fun setupRecyclerView() {
        adapter = ColorBottomSheetAdapter(this)
        list_color.layoutManager = GridLayoutManager(requireContext(), COUNT_OF_ROW)
        list_color.adapter = adapter

        adapter.addItems(colors)
    }

    override fun onItemClick(item: PrimaryColor, position: Int) {
        colors.forEach { it.selected = false }
        colors[position].selected = true
        adapter.addItems(colors)
        showPerfectToast(requireContext(), item.colorName)
    }

    companion object {
        const val COUNT_OF_ROW = 6
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialog.cancel()
        listener.chosenColor(colors)
    }

    override fun onDestroy() {
        super.onDestroy()
        colors.forEach { it.selected = false }
    }
}