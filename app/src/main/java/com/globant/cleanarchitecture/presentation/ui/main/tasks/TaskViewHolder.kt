package com.globant.cleanarchitecture.presentation.ui.main.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.cleanarchitecture.R
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import kotlinx.android.synthetic.main.row_task.view.*

class TaskViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup) = TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_task, parent, false)
        )
    }

    fun bindTo(task: TaskEntity, listener: TasksAdapter.OnClickListener) = with(itemView) {
        tvTitle.text = task.title
        tvDescription.text = task.description

        setOnClickListener {
            listener.onClick(task)
        }
    }
}