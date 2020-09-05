package com.globant.cleanarchitecture.presentation.components.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.globant.cleanarchitecture.R

object ProgressDialog : DialogFragment() {
    private const val TAG = "ProgressDialog"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.dialog_progress, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
    }

    fun show(manager: FragmentManager) {
        manager.executePendingTransactions()
        if (!isAdded) {
            show(manager, TAG)
        }
    }
}