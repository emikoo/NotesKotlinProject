package com.example.notesapp.di

import com.example.notesapp.data.network.client.*
import com.example.notesapp.ui.create.CreateProjectViewModel
import com.example.notesapp.ui.projects.ProjectViewModel
import com.example.notesapp.ui.repository.ProjectRepositoryImpl
import com.example.notesapp.ui.repository.TaskRepositoryImpl
import com.example.notesapp.ui.tasks.TaskListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProjectViewModel(get()) }
    viewModel { CreateProjectViewModel(get()) }
    viewModel { TaskListViewModel(get()) }
}

val repositoryModule = module {
    factory { ProjectRepositoryImpl(get()) }
    factory { TaskRepositoryImpl(get()) }
}

val networkRepository = module {
    single { RetrofitClient(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideProjectApi(get()) }
    single { provideTasksApi(get()) }
}