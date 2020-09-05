package com.globant.cleanarchitecture.data.local

import com.globant.cleanarchitecture.data.local.models.Task
import com.globant.cleanarchitecture.data.local.room.dao.TaskDao
import com.globant.cleanarchitecture.data.mapper.toModel
import com.globant.cleanarchitecture.data.repositories.TaskLocalDataSource
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

@Reusable
class TaskLocalDataSourceImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskLocalDataSource {
    override fun loadTasks(): Flowable<List<Task>> = taskDao.findAll()

    override fun saveTasks(tasks: List<TaskEntity>): Completable =
        taskDao.insertAll(tasks.toModels())

    private fun List<TaskEntity>.toModels() = map { task -> task.toModel() }

    override fun saveTask(task: TaskEntity): Completable =
        taskDao.insert(task.toModel())
            .ignoreElement()
}