package com.globant.cleanarchitecture.data.local.room

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.globant.cleanarchitecture.data.local.room.dao.TaskDao
import com.globant.cleanarchitecture.data.local.models.Task

@Database(entities = [
    Task::class
], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @VisibleForTesting
        private const val DATABASE_NAME = "ToDo-db"

        private val LOCK = Any()

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = getDatabase(context)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

        private fun getDatabase(context: Context): Builder<AppDatabase> =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
    }

    abstract fun taskDao(): TaskDao
}