package com.globant.cleanarchitecture.domain.usecases

import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.qualifiers.Background
import com.globant.cleanarchitecture.domain.qualifiers.Foreground
import com.globant.cleanarchitecture.domain.repositories.TaskRepository
import com.globant.cleanarchitecture.domain.usecases.base.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : CompletableUseCase<TaskEntity>(backgroundScheduler, foregroundScheduler) {
    override fun generateCompletable(input: TaskEntity?): Completable {
        requireNotNull(input) { "The task cannot be null" }
        return repository.saveTask(input)
    }
}