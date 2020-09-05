package com.globant.cleanarchitecture.domain.usecases.base

import io.reactivex.Flowable
import io.reactivex.Scheduler

abstract class FlowableUseCase<T, in Input>(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun generateFlowable(input: Input? = null): Flowable<T>

    operator fun invoke(input: Input? = null): Flowable<T> {
        return generateFlowable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}