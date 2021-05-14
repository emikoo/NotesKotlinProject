package com.example.notesapp.ui.create

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.data.network.ResponseResultStatus
import com.example.notesapp.ui.base.BaseViewModel
import com.example.notesapp.ui.repository.ProjectRepositoryImpl

class CreateProjectViewModel(private val repository: ProjectRepositoryImpl): BaseViewModel() {
    val createResult = MutableLiveData<Boolean>()

    fun createProject(name: String, selectedColor: Int?) {
        if (name.isEmpty()) {
            message.postValue("Имя проекта не может быть пустым")
            return
        }
        repository.createProject(name, selectedColor).observeForever {
            when(it.status) {
                ResponseResultStatus.SUCCESS -> createResult.value = it.result != null
                ResponseResultStatus.ERROR -> message.value = it.message
            }
        }
    }
}