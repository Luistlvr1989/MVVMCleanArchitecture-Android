package com.globant.cleanarchitecture.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.globant.cleanarchitecture.di.injector
import com.globant.cleanarchitecture.presentation.components.dialogs.ErrorDialog
import com.globant.cleanarchitecture.presentation.components.dialogs.ProgressDialog

abstract class BaseFragment<T : BaseViewModel> : BaseView<T>, Fragment {
    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    lateinit var viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        val factory = injector.viewModelFactory
        val viewModelClass = createViewModel()
        viewModel = ViewModelProvider(this, factory).get(viewModelClass)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            ProgressDialog.show(childFragmentManager)
        } else {
            ProgressDialog.dismiss()
        }
    }

    override fun showError(error: Int) = ErrorDialog.Builder(requireContext())
        .setMessage(error)
        .build()
        .show(childFragmentManager)
}