package com.example.notesapp.ui.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.model.Project
import com.example.notesapp.data.model.Task
import com.example.notesapp.data.network.ResponseResultStatus
import com.example.notesapp.ui.base.BaseViewModel
import com.example.notesapp.ui.repository.TaskRepositoryImpl

class TaskListViewModel(
    private val repository: TaskRepositoryImpl
) : BaseViewModel() {

    val data: MutableLiveData<MutableList<Task>>? = MutableLiveData()
    var project: Project? = null

    init {
        fetchAllProjectsTasks()
    }

    fun fetchAllProjectsTasks() {
        repository.fetchAllProjectsTasks(project?.id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> message?.value = it.message
                ResponseResultStatus.SUCCESS -> data?.value = it.result
            }
        }
    }

}