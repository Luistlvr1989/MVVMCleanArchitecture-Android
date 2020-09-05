package com.globant.cleanarchitecture.presentation.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.globant.cleanarchitecture.di.injector
import com.globant.cleanarchitecture.presentation.components.dialogs.ErrorDialog
import com.globant.cleanarchitecture.presentation.components.dialogs.ProgressDialog

abstract class BaseActivity<T : BaseViewModel> : BaseView<T>, AppCompatActivity {
    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        val factory = injector.viewModelFactory
        val viewModelClass = createViewModel()
        viewModel = ViewModelProvider(this, factory).get(viewModelClass)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            ProgressDialog.show(supportFragmentManager)
        } else {
            ProgressDialog.dismiss()
        }
    }

    override fun showError(error: Int) = ErrorDialog.Builder(this)
        .setMessage(error)
        .build()
        .show(supportFragmentManager)
}