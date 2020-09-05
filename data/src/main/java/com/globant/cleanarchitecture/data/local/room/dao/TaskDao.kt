package com.globant.cleanarchitecture.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.globant.cleanarchitecture.data.local.models.Task
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class TaskDao : BaseDao<Task> {
    @Transaction
    open fun updateData(tasks: List<Task>) {
        deleteAll()
        insertAll(tasks)
    }

    @Query("SELECT * FROM tasks ORDER BY created")
    abstract fun findAll(): Observable<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    abstract fun findById(id: Long): Observable<Task>

    @Query("DELETE FROM tasks WHERE id = :id")
    abstract fun deleteById(id: Long): Completable

    @Query("DELETE FROM tasks")
    abstract fun deleteAll()
}