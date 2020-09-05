package com.globant.cleanarchitecture.di

import com.globant.cleanarchitecture.App
import com.globant.cleanarchitecture.di.components.GraphComponent

interface DaggerComponentProvider {
    val component: GraphComponent
}

val injector get() = (App.context as DaggerComponentProvider).component