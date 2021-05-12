package com.example.notesapp.ui.repository

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.data.model.Project
import com.example.notesapp.data.network.ResponseResult
import com.example.notesapp.data.network.api.ProjectApi
import com.example.notesapp.data.network.client.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ProjectRepository {
    fun fetchProjects(): MutableLiveData<ResponseResult<MutableList<Project>>>
    fun deleteProject(id: Long?): MutableLiveData<ResponseResult<Int>>
    fun createProject(name: String): MutableLiveData<ResponseResult<Project>>
}

class ProjectRepositoryImpl(private val api: ProjectApi): ProjectRepository {
    override fun fetchProjects(): MutableLiveData<ResponseResult<MutableList<Project>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Project>>> = MutableLiveData(ResponseResult.loading())
        api.getAllProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(
                call: Call<MutableList<Project>>,
                response: Response<MutableList<Project>>
            ) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

    override fun deleteProject(id: Long?): MutableLiveData<ResponseResult<Int>> {
        val data: MutableLiveData<ResponseResult<Int>>
                = MutableLiveData(ResponseResult.loading())
        api.deleteProject(id).enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) data.value = ResponseResult.success(response.code())
            }
        })
        return data
    }

    override fun createProject(name: String): MutableLiveData<ResponseResult<Project>> {
        val project = Project(name = name)
        val data: MutableLiveData<ResponseResult<Project>> = MutableLiveData(ResponseResult.loading())
        api.createProject(project).enqueue(object : Callback<Project> {
            override fun onFailure(call: Call<Project>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                data.value = ResponseResult.success(response.body())
            }

        })
        return data
    }


}