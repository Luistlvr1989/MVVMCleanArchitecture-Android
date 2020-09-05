package com.globant.cleanarchitecture

import android.app.Application
import com.globant.cleanarchitecture.di.DaggerComponentProvider
import com.globant.cleanarchitecture.di.components.DaggerGraphComponent
import com.globant.cleanarchitecture.di.components.GraphComponent
import com.globant.cleanarchitecture.utils.TimberUtils

class App : Application(), DaggerComponentProvider {
    companion object {
        lateinit var context: App
    }

    override val component: GraphComponent by lazy {
        DaggerGraphComponent.builder()
            .applicationContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        TimberUtils.init(BuildConfig.DEBUG)
    }
}