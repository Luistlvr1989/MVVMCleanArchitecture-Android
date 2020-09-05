package com.globant.cleanarchitecture.di.modules

import android.app.Application
import com.globant.cleanarchitecture.data.local.TaskLocalDataSourceImpl
import com.globant.cleanarchitecture.data.local.room.AppDatabase
import com.globant.cleanarchitecture.data.repositories.TaskLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalModule.Binders::class])
class LocalModule {
    @Module
    interface Binders {
        @Binds
        fun bindTaskLocalDataSource(
            localDataSourceImpl: TaskLocalDataSourceImpl
        ): TaskLocalDataSource
    }

    @Provides
    @Singleton
    fun providesDatabase(application: Application) = AppDatabase.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesTaskDao(database: AppDatabase) = database.taskDao()
}