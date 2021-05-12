package com.example.notesapp.ui.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.model.Project
import com.example.notesapp.data.network.ResponseResultStatus
import com.example.notesapp.ui.base.BaseViewModel
import com.example.notesapp.ui.repository.ProjectRepositoryImpl

class ProjectViewModel(private val repository: ProjectRepositoryImpl) : BaseViewModel() {

    var project = mutableListOf<Project>()
    val data = MutableLiveData<MutableList<Project>>()

    fun fetchProjects() {
        repository.fetchProjects().observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> {
                    message.value = it.message
                    loading.value = false
                }
                ResponseResultStatus.SUCCESS -> {
                    data.value = it.result
                    loading.value = false
                }
                ResponseResultStatus.LOADING -> loading.value = true
            }
        }
    }

    fun deleteProject(id: Long?) {
        repository.deleteProject(id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> {
                    message.value = it.message
                    loading.value = false
                }
                ResponseResultStatus.SUCCESS -> {
                    handleResult(it.result)
                    loading.value = false
                }
                ResponseResultStatus.LOADING -> loading.value = true
            }
        }
    }

    private fun handleResult(code: Int?) {
        if (code == 204) message.value = "Проект успешно удален"
    }

}