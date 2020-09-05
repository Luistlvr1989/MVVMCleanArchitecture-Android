package com.globant.cleanarchitecture.data.repositories

import com.globant.cleanarchitecture.data.local.models.Task
import com.globant.cleanarchitecture.data.mapper.toEntity
import com.globant.cleanarchitecture.data.remote.models.TaskDto
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.repositories.TaskRepository
import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class TaskRepositoryImpl @Inject constructor(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource
) : TaskRepository {
    override fun getTasks(): Observable<List<TaskEntity>> = remoteDataSource.fetchTasks()
        .flatMapCompletable { dtos ->
            val entities = dtos.map { dto -> dto.toEntity() }
            localDataSource.saveTasks(entities)
        }.andThen(loadTasks())

    private fun loadTasks(): Observable<List<TaskEntity>> = localDataSource.loadTasks()
        .map { tasks -> tasks.toEntities() }

    private fun List<Task>.toEntities() = map { task ->
        task.toEntity()
    }

    override fun saveTask(task: TaskEntity): Completable = localDataSource.saveTask(task)
}

interface TaskLocalDataSource {
    fun loadTasks(): Observable<List<Task>>

    fun saveTasks(tasks: List<TaskEntity>): Completable

    fun saveTask(task: TaskEntity): Completable
}

interface TaskRemoteDataSource {
    fun fetchTasks(): Single<List<TaskDto>>
}