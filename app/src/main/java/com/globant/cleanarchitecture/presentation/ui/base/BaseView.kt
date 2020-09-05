package com.globant.cleanarchitecture.presentation.ui.base

interface BaseView<T : BaseViewModel> {
    fun createViewModel(): Class<out T>

    fun showProgress(show: Boolean)

    fun showError(error: Int)
}