package com.example.notesapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.helper.showToast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel>(
    private val layoutId: Int,
    private val clazz: KClass<VM>
) : AppCompatActivity() {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        viewModel = getViewModel(clazz = clazz)
        setupViews()
        subscribeToLiveData()
    }

    private fun subscribeToMessages() {
        viewModel.message.observeForever {
            showToast(it)
        }
    }

    abstract fun setupViews()
    abstract fun subscribeToLiveData()
}