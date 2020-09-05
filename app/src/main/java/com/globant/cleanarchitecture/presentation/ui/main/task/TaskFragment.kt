package com.globant.cleanarchitecture.presentation.ui.main.task

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.globant.cleanarchitecture.R
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.extensions.content.goBack
import com.globant.cleanarchitecture.extensions.widget.trim
import com.globant.cleanarchitecture.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_task.*

class TaskFragment : BaseFragment<TaskViewModel>(R.layout.fragment_main_task) {
    private val args: TaskFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        prepareUI()
    }

    override fun createViewModel() = TaskViewModel::class.java

    private fun setupObservers() = with(viewModel) {
        isLoading.observe(viewLifecycleOwner, ::showProgress)
        error.observe(viewLifecycleOwner, ::showError)

        isCompleted.observe(viewLifecycleOwner) {
            goBack()
        }
    }

    private fun prepareUI() {
        with(args.task) {
            etTitle.setText(title)
            etDescription.setText(description)
        }

        bSave.setOnClickListener {
            saveTask(args.task)
        }
    }

    private fun saveTask(task: TaskEntity) {
        task.title = etTitle.trim()
        task.description = etDescription.trim()
        viewModel.saveTask(task)
    }
}