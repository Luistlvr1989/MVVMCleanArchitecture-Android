package com.globant.cleanarchitecture.domain.usecases

import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.repositories.TaskRepository
import com.globant.cleanarchitecture.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository,
    background: CoroutineDispatcher
) : UseCase<List<TaskEntity>, Void>(background) {
    override suspend fun run(input: Void?): List<TaskEntity> = repository.getTasks()
}