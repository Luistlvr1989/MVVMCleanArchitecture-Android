package com.globant.cleanarchitecture.data.remote

import com.globant.cleanarchitecture.data.remote.api.TaskService
import com.globant.cleanarchitecture.data.remote.models.TaskDto
import com.globant.cleanarchitecture.data.repositories.TaskRemoteDataSource
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class TaskRemoteDataSourceImpl @Inject constructor(
    private val taskService: TaskService,
) : TaskRemoteDataSource {
    override fun fetchTasks(): Single<List<TaskDto>> = taskService.fetchTasks()
}