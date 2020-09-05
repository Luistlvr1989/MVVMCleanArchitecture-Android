package com.globant.cleanarchitecture.domain.repositories

import com.globant.cleanarchitecture.domain.entities.TaskEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface TaskRepository {
    fun getTasks(): Flowable<List<TaskEntity>>

    fun saveTask(task: TaskEntity): Completable
}