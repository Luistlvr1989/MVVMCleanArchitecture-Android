package com.globant.cleanarchitecture.data.remote.api

import com.globant.cleanarchitecture.data.remote.models.TaskDto
import io.reactivex.Single
import retrofit2.http.GET

interface TaskService {
    companion object {
        private const val VERSION = "v2"
    }

    @GET("$VERSION/5e52db362d0000f622357be6")
    fun fetchTasks(): Single<List<TaskDto>>
}