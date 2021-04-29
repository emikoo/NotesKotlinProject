package com.example.notesapp.ui.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.model.Project
import com.example.notesapp.ui.repository.ProjectRepositoryImpl

class ProjectViewModel : ViewModel() {
    private val repository = ProjectRepositoryImpl()
    val data: MutableLiveData<MutableList<Project>>? = MutableLiveData()
    var project = mutableListOf<Project>()
    val message: MutableLiveData<String>? = MutableLiveData()

    init {
        subscribeToData()
        subscribeToMessage()
        repository.fetchProjects()
    }

    fun subscribeToData() {
        repository.data?.observeForever {
            project = it
            data?.value = it
        }
    }

    fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }

}