package com.lucianoluzzi.workout.feed.domain

import com.lucianoluzzi.domain.Workout

interface FeedRepository {
    suspend fun fetchWorkouts(): List<Workout>
}