package com.globant.cleanarchitecture.presentation.ui.main.tasks

import android.os.Bundle
import android.view.View
import com.globant.cleanarchitecture.R
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.extensions.content.navigate
import com.globant.cleanarchitecture.presentation.components.decoration.SimpleDividerItemDecoration
import com.globant.cleanarchitecture.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_tasks.*

class TasksFragment : BaseFragment<TasksViewModel>(R.layout.fragment_main_tasks), TasksAdapter.OnClickListener {
    private val tasksAdapter = TasksAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUI()
    }

    override fun createViewModel() = TasksViewModel::class.java

    private fun prepareUI() {
        bAddTask.setOnClickListener {
            goToTaskDetails()
        }

        rvTasks.apply {
            addItemDecoration(SimpleDividerItemDecoration(context))
            adapter = tasksAdapter
        }

        loadTasks()
    }

    private fun goToTaskDetails(task: TaskEntity = TaskEntity()) {
        val action = TasksFragmentDirections.actionTasksToTask(task)
        navigate(action)
    }

    private fun loadTasks() = with(viewModel) {
        getTasks().observe(viewLifecycleOwner) { tasks ->
            tasksAdapter.refresh(tasks)
        }
    }

    override fun onClick(entity: TaskEntity) {
        goToTaskDetails(entity)
    }
}