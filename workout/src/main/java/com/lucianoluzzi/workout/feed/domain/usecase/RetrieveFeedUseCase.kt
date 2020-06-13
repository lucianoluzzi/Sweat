package com.lucianoluzzi.workout.feed.domain.usecase

import com.lucianoluzzi.domain.Workout
import kotlinx.coroutines.flow.Flow

interface RetrieveFeedUseCase {
    fun retrieveFeed(): Flow<List<Workout>>
}