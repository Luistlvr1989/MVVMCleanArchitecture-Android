package com.globant.cleanarchitecture.di.modules

import com.globant.cleanarchitecture.BuildConfig
import com.globant.cleanarchitecture.data.remote.TaskRemoteDataSourceImpl
import com.globant.cleanarchitecture.data.remote.api.TaskService
import com.globant.cleanarchitecture.data.repositories.TaskRemoteDataSource
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {
    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

        private val gson = GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .create()
    }

    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            taskRemoteDataSourceImpl: TaskRemoteDataSourceImpl
        ): TaskRemoteDataSource
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val interceptors: List<Interceptor> = ArrayList(listOf(logging))

        return OkHttpClient.Builder()
            .apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
            }
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    fun provideTaskService(retrofit: Retrofit): TaskService = retrofit.create(TaskService::class.java)
}