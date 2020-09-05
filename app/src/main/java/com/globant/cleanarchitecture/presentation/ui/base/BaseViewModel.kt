package com.globant.cleanarchitecture.presentation.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

abstract class BaseViewModel : ViewModel() {
    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun Disposable.autoDispose() {
        disposable += this
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}