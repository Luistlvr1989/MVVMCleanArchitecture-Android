package com.globant.cleanarchitecture.di.modules

import com.globant.cleanarchitecture.data.local.TaskLocalDataSourceImpl
import com.globant.cleanarchitecture.data.repositories.TaskLocalDataSource
import dagger.Binds
import dagger.Module

@Module(includes = [LocalModule.Binders::class])
class LocalModule {
    @Module
    interface Binders {
        @Binds
        fun bindTaskLocalDataSource(
            localDataSourceImpl: TaskLocalDataSourceImpl
        ): TaskLocalDataSource
    }
}