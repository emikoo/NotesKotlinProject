package com.example.notesapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val message = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
}