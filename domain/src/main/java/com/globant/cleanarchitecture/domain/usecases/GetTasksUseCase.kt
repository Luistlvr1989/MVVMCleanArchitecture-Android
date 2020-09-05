package com.globant.cleanarchitecture.domain.usecases

import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.qualifiers.Background
import com.globant.cleanarchitecture.domain.qualifiers.Foreground
import com.globant.cleanarchitecture.domain.repositories.TaskRepository
import com.globant.cleanarchitecture.domain.usecases.base.FlowableUseCase
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : FlowableUseCase<List<TaskEntity>, Void>(backgroundScheduler, foregroundScheduler) {
    override fun generateFlowable(input: Void?): Flowable<List<TaskEntity>> = repository.getTasks()
}