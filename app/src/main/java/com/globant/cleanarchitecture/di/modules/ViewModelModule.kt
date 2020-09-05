package com.globant.cleanarchitecture.di.modules

import androidx.lifecycle.ViewModelProvider
import com.globant.cleanarchitecture.di.factories.ViewModelFactory
import com.globant.cleanarchitecture.di.modules.viewmodels.MainViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [
    MainViewModelModule::class
])
abstract class ViewModelModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}