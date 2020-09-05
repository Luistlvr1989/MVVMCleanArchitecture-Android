package com.globant.cleanarchitecture.data.repositories

import com.globant.cleanarchitecture.data.local.models.Task
import com.globant.cleanarchitecture.data.mapper.toEntity
import com.globant.cleanarchitecture.data.remote.models.TaskDto
import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.repositories.TaskRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskRepositoryImpl @Inject constructor(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource
) : TaskRepository {
    override suspend fun getTasks(): List<TaskEntity> {
        syncRemoteTasks()
        return localDataSource.loadTasks().map { task ->
            task.toEntity()
        }
    }

    private suspend fun syncRemoteTasks() {
        val taskEntities = remoteDataSource.fetchTasks().map { dto ->
            dto.toEntity()
        }

        localDataSource.saveTasks(taskEntities)
    }

    override suspend fun saveTask(task: TaskEntity) = localDataSource.saveTask(task)
}

interface TaskLocalDataSource {
    suspend fun loadTasks(): List<Task>

    suspend fun saveTasks(tasks: List<TaskEntity>)

    suspend fun saveTask(task: TaskEntity)
}

interface TaskRemoteDataSource {
    suspend fun fetchTasks(): List<TaskDto>
}