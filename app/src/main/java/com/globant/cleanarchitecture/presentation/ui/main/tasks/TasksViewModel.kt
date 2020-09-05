package com.globant.cleanarchitecture.presentation.ui.main.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.usecases.GetTasksUseCase
import com.globant.cleanarchitecture.presentation.ui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : BaseViewModel() {
    private val tasks = MutableLiveData<List<TaskEntity>>()

    fun getTasks(): LiveData<List<TaskEntity>> = tasks.also {
        getTasksUseCase()
            .subscribe({
                tasks.value = it
            }, ::onError)
            .autoDispose()
    }

    private fun onError(error: Throwable) = Timber.e(error)
}