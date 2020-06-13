package com.lucianoluzzi.workout.di

import com.lucianoluzzi.workout.feed.repository.FeedRepositoryImpl
import com.lucianoluzzi.workout.feed.ui.FeedFragmentFactory
import com.lucianoluzzi.workout.feed.ui.viewModel.FeedViewModel
import com.lucianoluzzi.workout.feed.usecase.RetrieveFeedUseCaseImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WorkoutModule {
    val module = module {
        viewModel {
            val retrieveFeedUseCase = RetrieveFeedUseCaseImpl(FeedRepositoryImpl())
            FeedViewModel(retrieveFeedUseCase)
        }

        factory {
            FeedFragmentFactory(get() as FeedViewModel)
        }
    }
}