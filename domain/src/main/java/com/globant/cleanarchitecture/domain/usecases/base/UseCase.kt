package com.globant.cleanarchitecture.domain.usecases.base

import com.globant.cleanarchitecture.domain.usecases.base.Response.Failure
import com.globant.cleanarchitecture.domain.usecases.base.Response.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<T, in Input>(private val background: CoroutineDispatcher) {
    protected abstract suspend fun run(input: Input? = null): T

    suspend operator fun invoke(input: Input? = null): Response<T> = withContext(background) {
        try {
            Success(run(input))
        } catch (error: Exception) {
            Failure(error)
        }
    }
}