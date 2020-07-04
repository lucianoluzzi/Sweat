package com.lucianoluzzi.workout.feed.ui.viewModel

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.domain.Workout
import com.lucianoluzzi.workout.feed.domain.usecase.RetrieveFeedUseCase
import com.lucianoluzzi.workout.post.ui.viewmodel.CoroutineScopeExtension
import com.lucianoluzzi.workout.post.ui.viewmodel.InstantExecutorExtension
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Answers

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutineScopeExtension::class)
class FeedViewModelTest {
    private val useCase = mock<RetrieveFeedUseCase>(defaultAnswer = Answers.RETURNS_DEEP_STUBS)

    @Test
    fun `assert viewModel exposes useCase data`() {
        val mockedFeedList = listOf(
            Workout(listOf()),
            Workout(listOf()),
            Workout(listOf())
        )
        val mockedFlow = flow {
            emit(mockedFeedList)
        }

        whenever(useCase.retrieveFeed()).thenReturn(mockedFlow)
        val viewModel = FeedViewModel(useCase)

        val workoutsValue = viewModel.workouts.value
        assertThat(workoutsValue).isEqualTo(mockedFeedList)
    }
}