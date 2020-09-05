package com.globant.cleanarchitecture.data.local.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(element: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(elements: List<T>)

    @Update
    suspend fun update(vararg element: T)

    @Delete
    suspend fun delete(element: T)

    @Delete
    suspend fun deleteAll(elements: List<T>)
}