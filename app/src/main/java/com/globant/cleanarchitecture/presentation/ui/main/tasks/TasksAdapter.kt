package com.globant.cleanarchitecture.presentation.ui.main.tasks

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.cleanarchitecture.domain.entities.TaskEntity

class TasksAdapter(private val listener: OnClickListener) : RecyclerView.Adapter<TaskViewHolder>() {
    private var tasks = ArrayList<TaskEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder.create(parent)

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bindTo(task, listener)
    }

    override fun getItemCount(): Int = tasks.size

    fun refresh(tasks: List<TaskEntity>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    fun getTaskByPosition(position: Int) = tasks[position]

    interface OnClickListener {
        fun onClick(entity: TaskEntity)
    }
}