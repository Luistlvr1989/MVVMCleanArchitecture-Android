package com.globant.cleanarchitecture.di.modules

import com.globant.cleanarchitecture.data.repositories.TaskRepositoryImpl
import com.globant.cleanarchitecture.domain.repositories.TaskRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindTaskRepository(repoImpl: TaskRepositoryImpl): TaskRepository
}