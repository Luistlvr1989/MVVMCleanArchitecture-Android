package com.globant.cleanarchitecture.domain

import com.globant.cleanarchitecture.domain.entities.TaskEntity
import com.globant.cleanarchitecture.domain.repositories.TaskRepository
import com.globant.cleanarchitecture.domain.usecases.SaveTaskUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.*

@RunWith(JUnit4::class)
class SaveTaskUseCaseTest {
    @Mock
    private lateinit var repository: TaskRepository

    private lateinit var saveTaskUseCase: SaveTaskUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        saveTaskUseCase = SaveTaskUseCase(
            repository,
            Schedulers.trampoline(),
            Schedulers.trampoline())
    }

    @Test
    fun `save task successfully`() {
        val entity = mock(TaskEntity::class.java)

        `when`(repository.saveTask(entity))
            .thenReturn(Completable.complete())

        val observable: Completable = saveTaskUseCase(entity)
        val testObserver = observable.test()
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
        testObserver.dispose()
    }

    @Test
    fun `save task failure`() {
        val entity = mock(TaskEntity::class.java)
        val exception = RuntimeException()

        `when`(repository.saveTask(entity))
            .thenReturn(Completable.error(exception))

        val observable: Completable = saveTaskUseCase(entity)
        val testObserver = observable.test()
            .assertSubscribed()
            .assertError(exception)
        testObserver.dispose()
    }
}