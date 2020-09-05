package com.globant.cleanarchitecture.di.modules.viewmodels

import androidx.lifecycle.ViewModel
import com.globant.cleanarchitecture.di.factories.ViewModelKey
import com.globant.cleanarchitecture.presentation.ui.main.task.TaskViewModel
import com.globant.cleanarchitecture.presentation.ui.main.tasks.TasksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(viewModel: TasksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TaskViewModel::class)
    abstract fun bindTaskViewModel(viewModel: TaskViewModel): ViewModel
}