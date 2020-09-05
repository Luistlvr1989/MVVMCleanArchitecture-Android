package com.globant.cleanarchitecture.presentation.ui.main.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.usecases.GetTasksUseCase
import com.globant.cleanarchitecture.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : BaseViewModel() {
    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mIsLoading

    private val tasks = MutableLiveData<List<TaskEntity>>()

    fun getTasks(): LiveData<List<TaskEntity>> = tasks.also {
        viewModelScope.launch(Main) {
            mIsLoading.value = true
            getTasksUseCase().fold({
                mIsLoading.value = false
                tasks.setValue(it)
            }, ::onError)
        }
    }

    private fun onError(error: Throwable) {
        Timber.e(error)
        mIsLoading.value = false
    }
}