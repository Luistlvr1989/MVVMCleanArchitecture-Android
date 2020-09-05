package com.globant.cleanarchitecture.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO
}