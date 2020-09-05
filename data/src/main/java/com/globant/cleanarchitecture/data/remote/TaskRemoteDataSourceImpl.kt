package com.globant.cleanarchitecture.data.remote

import com.globant.cleanarchitecture.data.remote.api.TaskService
import com.globant.cleanarchitecture.data.remote.models.TaskDto
import com.globant.cleanarchitecture.data.repositories.TaskRemoteDataSource
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskRemoteDataSourceImpl @Inject constructor(
    private val taskService: TaskService,
) : TaskRemoteDataSource {
    override suspend fun fetchTasks(): List<TaskDto> = taskService.fetchTasks()
}