package com.globant.cleanarchitecture.domain.usecases.base

import io.reactivex.Maybe
import io.reactivex.Scheduler

abstract class MaybeUseCase<T, in Input>(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun generateMaybe(input: Input? = null): Maybe<T>

    operator fun invoke(input: Input? = null): Maybe<T> {
        return generateMaybe(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}