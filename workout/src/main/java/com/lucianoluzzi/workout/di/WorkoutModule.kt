package com.lucianoluzzi.workout.di

import com.lucianoluzzi.workout.WorkoutFragmentFactory
import com.lucianoluzzi.workout.feed.domain.usecase.RetrieveFeedUseCaseImpl
import com.lucianoluzzi.workout.feed.repository.FeedRepositoryImpl
import com.lucianoluzzi.workout.feed.ui.viewModel.FeedViewModel
import com.lucianoluzzi.workout.post.domain.usecase.GetExercisesUseCase
import com.lucianoluzzi.workout.post.ui.viewmodel.PostWorkoutViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WorkoutModule {
    val module = module {
        viewModel {
            val retrieveFeedUseCase = RetrieveFeedUseCaseImpl(FeedRepositoryImpl())
            FeedViewModel(retrieveFeedUseCase)
        }

        viewModel {
            PostWorkoutViewModel(GetExercisesUseCase())
        }

        factory {
            WorkoutFragmentFactory(
                get() as FeedViewModel
            )
        }
    }
}