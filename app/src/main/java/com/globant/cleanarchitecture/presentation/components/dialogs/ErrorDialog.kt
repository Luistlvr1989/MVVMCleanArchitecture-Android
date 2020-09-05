package com.globant.cleanarchitecture.presentation.components.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.globant.cleanarchitecture.R
import kotlinx.android.synthetic.main.dialog_error.view.*

object ErrorDialog : DialogFragment(R.layout.dialog_error) {
    private const val TAG = "ErrorDialog"

    private var message: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        view.prepareUI()
    }

    private fun View.prepareUI() {
        tvMessage.text = message
        bOk.setOnClickListener {
            dismiss()
        }
    }

    class Builder(private val context: Context) {
        fun setMessage(@StringRes id: Int) = this.apply {
            setMessage(context.getString(id))
        }

        fun setMessage(text: String) = this.apply {
            message = text
        }

        fun build() = ErrorDialog
    }

    fun show(manager: FragmentManager) {
        manager.executePendingTransactions()
        if (!isAdded) {
            show(manager, TAG)
        }
    }
}