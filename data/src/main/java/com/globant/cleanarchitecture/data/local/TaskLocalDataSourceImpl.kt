package com.globant.cleanarchitecture.data.local

import com.globant.cleanarchitecture.data.local.models.Task
import com.globant.cleanarchitecture.data.local.room.dao.TaskDao
import com.globant.cleanarchitecture.data.mapper.toModel
import com.globant.cleanarchitecture.data.repositories.TaskLocalDataSource
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskLocalDataSourceImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskLocalDataSource {
    override suspend fun loadTasks(): List<Task> = taskDao.findAll()

    override suspend fun saveTasks(tasks: List<TaskEntity>) = taskDao.insertAll(tasks.toModels())

    private fun List<TaskEntity>.toModels() = map { task -> task.toModel() }

    override suspend fun saveTask(task: TaskEntity) {
        taskDao.insert(task.toModel())
    }
}