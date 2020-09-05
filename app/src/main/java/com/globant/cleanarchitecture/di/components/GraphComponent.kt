package com.globant.cleanarchitecture.di.components

import android.app.Application
import com.globant.cleanarchitecture.di.factories.ViewModelFactory
import com.globant.cleanarchitecture.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    //ViewModelModule::class,
    DomainModule::class,
    DataModule::class,
    LocalModule::class,
    RemoteModule::class
])
interface GraphComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(application: Application): Builder

        fun build(): GraphComponent
    }

    //val viewModelFactory: ViewModelFactory
}