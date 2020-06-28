package com.lucianoluzzi.workout.post.ui.viewmodel

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.workout.post.domain.usecase.GetExercisesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineScopeExtension::class, InstantExecutorExtension::class)
class PostWorkoutViewModelTest {
    private val getExercisesUseCase = mock<GetExercisesUseCase>()

    @Test
    fun `assert view model exposes usecase data`() = runBlockingTest {
        val mockedList = listOf(
            "Exercise 1",
            "Exercise 2",
            "Exercise 3"
        )

        whenever(getExercisesUseCase.invoke()).thenReturn(mockedList)
        val viewModel = PostWorkoutViewModel(getExercisesUseCase)

        assertThat(viewModel.exercises.value).isEqualTo(mockedList)
    }
}