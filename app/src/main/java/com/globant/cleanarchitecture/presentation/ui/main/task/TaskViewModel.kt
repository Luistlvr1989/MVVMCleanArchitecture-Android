package com.globant.cleanarchitecture.presentation.ui.main.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.globant.cleanarchitecture.R
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.usecases.SaveTaskUseCase
import com.globant.cleanarchitecture.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val saveTaskUseCase: SaveTaskUseCase
) : BaseViewModel() {
    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mIsLoading

    private var mError = MutableLiveData<Int>()
    var error: LiveData<Int> = mError

    private var mIsCompleted = MutableLiveData<Boolean>()
    var isCompleted: LiveData<Boolean> = mIsCompleted

    fun saveTask(task: TaskEntity) {
        saveTaskUseCase(task)
            .doOnSubscribe { mIsLoading.value = true }
            .doOnTerminate { mIsLoading.value = false }
            .subscribe({
                mIsCompleted.value = true
            }, {
                mError.value = R.string.error_save
            }).autoDispose()
    }
}