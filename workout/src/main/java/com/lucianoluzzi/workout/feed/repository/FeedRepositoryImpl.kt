package com.lucianoluzzi.workout.feed.repository

import com.lucianoluzzi.domain.Workout
import com.lucianoluzzi.workout.feed.domain.FeedRepository

class FeedRepositoryImpl() : FeedRepository {
    override suspend fun fetchWorkouts(): List<Workout> = listOf()
}