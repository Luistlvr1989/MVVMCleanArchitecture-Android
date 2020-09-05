package com.globant.cleanarchitecture.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.globant.cleanarchitecture.data.local.models.Task

@Dao
abstract class TaskDao : BaseDao<Task> {
    @Query("SELECT * FROM tasks ORDER BY created")
    abstract suspend fun findAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE id = :id")
    abstract suspend fun findById(id: Long): Task

    @Query("DELETE FROM tasks WHERE id = :id")
    abstract suspend fun deleteById(id: Long)

    @Query("DELETE FROM tasks")
    abstract suspend fun deleteAll()
}