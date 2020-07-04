package com.lucianoluzzi.workout.post.ui.viewmodel

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.workout.post.domain.usecase.GetExercisesUseCase
import com.lucianoluzzi.workout.post.ui.uimodel.WorkoutLineModel
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
    private val viewModel = PostWorkoutViewModel(getExercisesUseCase)

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

    @Test
    fun `assert workout lines initial state is empty`() {
        assertThat(viewModel.workoutLines).isEmpty()
    }

    @Test
    fun `assert workout lines is equal to added list`() {
        val workoutLineModelList = getMockedWorkoutLines()
        workoutLineModelList.forEach {
            viewModel.addWorkoutLine(it)
        }

        assertThat(viewModel.workoutLines).isEqualTo(workoutLineModelList)
    }

    @Test
    fun `assert workout lines decreases when removeWorkoutLine`() {
        val workoutLineModelList = getMockedWorkoutLines()
        workoutLineModelList.forEach {
            viewModel.addWorkoutLine(it)
        }

        val lastIndex = workoutLineModelList.size - 1
        viewModel.removeWorkoutLine(workoutLineModelList[lastIndex])

        assertThat(viewModel.workoutLines).hasSize(2)
    }

    private fun getMockedWorkoutLines(): List<WorkoutLineModel> = listOf(
        WorkoutLineModel("Rosca direta", "20", "15"),
        WorkoutLineModel("Rosca direta", "20", "15"),
        WorkoutLineModel("Rosca direta", "20", "15")
    )
}