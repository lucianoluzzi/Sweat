package com.lucianoluzzi.workout.post.data

import android.app.Activity
import com.lucianoluzzi.analytics.AnalyticsTracker

class PostWorkoutTracker(private val analyticsTracker: AnalyticsTracker) {

    fun trackScreenShown(activity: Activity) = analyticsTracker.trackScreen(activity, SCREEN_NAME)

    fun trackName(name: String) {
        analyticsTracker.trackEvent(
            WORKOUT_NAME_FILLED, mapOf(
                WORKOUT_NAME to name
            )
        )
    }

    fun trackWeight(weight: String) {
        analyticsTracker.trackEvent(
            WORKOUT_WEIGHT_FILLED, mapOf(
                WORKOUT_WEIGHT to weight
            )
        )
    }

    fun trackRepetitions(repetitions: String) {
        analyticsTracker.trackEvent(
            WORKOUT_REPETITIONS_FILLED, mapOf(
                WORKOUT_REPETITIONS to repetitions
            )
        )
    }

    fun trackAddButton() = analyticsTracker.trackEvent(ADD_BUTTON_CLICKED)
    fun trackDeleteButton() = analyticsTracker.trackEvent(DELETE_BUTTON_CLICKED)

    private companion object {
        const val SCREEN_NAME = "Post workout"

        const val WORKOUT_NAME_FILLED = "WORKOUT_NAME_FILLED"
        const val WORKOUT_NAME = "WORKOUT_NAME"

        const val WORKOUT_WEIGHT_FILLED = "WORKOUT_WEIGHT_FILLED"
        const val WORKOUT_WEIGHT = "WORKOUT_WEIGHT"

        const val WORKOUT_REPETITIONS_FILLED = "WORKOUT_REPETITIONS_FILLED"
        const val WORKOUT_REPETITIONS = "WORKOUT_REPETITIONS"

        const val ADD_BUTTON_CLICKED = "ADD_BUTTON_CLICKED"
        const val DELETE_BUTTON_CLICKED = "DELETE_BUTTON_CLICKED"
    }
}