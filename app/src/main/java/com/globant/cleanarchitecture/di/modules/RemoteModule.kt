package com.globant.cleanarchitecture.di.modules

import com.globant.cleanarchitecture.data.remote.TaskRemoteDataSourceImpl
import com.globant.cleanarchitecture.data.repositories.TaskRemoteDataSource
import dagger.Binds
import dagger.Module

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {
    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            taskRemoteDataSourceImpl: TaskRemoteDataSourceImpl
        ): TaskRemoteDataSource
    }
}