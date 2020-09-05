package com.globant.cleanarchitecture.domain.repositories

import com.globant.cleanarchitecture.domain.entities.TaskEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface TaskRepository {
    fun getTasks(): Observable<List<TaskEntity>>

    fun saveTask(task: TaskEntity): Completable
}