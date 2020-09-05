package com.globant.cleanarchitecture.presentation.ui.main.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.globant.cleanarchitecture.R
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.usecases.SaveTaskUseCase
import com.globant.cleanarchitecture.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import timber.log.Timber
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

    fun saveTask(task: TaskEntity) = viewModelScope.launch(Main) {
        mIsLoading.value = true
        saveTaskUseCase(task).fold({
            mIsLoading.value = false
            mIsCompleted.setValue(true)
        }, ::onError)
    }

    private fun onError(error: Exception) {
        Timber.e(error)
        mIsLoading.value = false
        mError.value = R.string.error_save
    }
}