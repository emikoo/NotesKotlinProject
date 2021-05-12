package com.example.notesapp.ui.repository

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.data.model.Task
import com.example.notesapp.data.network.ResponseResult
import com.example.notesapp.data.network.api.TaskApi
import com.example.notesapp.data.network.client.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface TaskRepository{
    fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>>
}

class TaskRepositoryImpl(private val api: TaskApi) : TaskRepository {

    override fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Task>>> = MutableLiveData(ResponseResult.loading())
        api.getTasks(id).enqueue(object : Callback<MutableList<Task>> {

            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<MutableList<Task>>, response: Response<MutableList<Task>>) {
                data.value =
                    if (response.isSuccessful) ResponseResult.success(response.body())
                    else ResponseResult.error(response.message())
            }
        })
        return data
    }
}
