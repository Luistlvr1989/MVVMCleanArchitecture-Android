package com.globant.cleanarchitecture.di.modules

import com.globant.cleanarchitecture.domain.qualifiers.Background
import com.globant.cleanarchitecture.domain.qualifiers.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    @Background
    fun providesBackgroundScheduler(): Scheduler = Schedulers.io()

    @Singleton
    @Provides
    @Foreground
    fun providesForegroundScheduler(): Scheduler = AndroidSchedulers.mainThread()
}