package com.lucianoluzzi.workout.feed.domain.usecase

import com.lucianoluzzi.domain.Workout
import com.lucianoluzzi.workout.feed.domain.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrieveFeedUseCaseImpl(private val repository: FeedRepository) : RetrieveFeedUseCase {
    override fun retrieveFeed(): Flow<List<Workout>> = flow { repository.fetchWorkouts() }
}