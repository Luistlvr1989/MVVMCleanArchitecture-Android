package com.globant.cleanarchitecture.domain.repositories

import com.globant.cleanarchitecture.domain.entities.TaskEntity

interface TaskRepository {
    suspend fun getTasks(): List<TaskEntity>

    suspend fun saveTask(task: TaskEntity)
}