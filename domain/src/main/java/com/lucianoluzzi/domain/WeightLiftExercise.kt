package com.lucianoluzzi.domain

import java.io.Serializable

data class WeightLiftExercise(
    override val name: String,
    override val imageUrl: String? = null,
    val repetitions: Int,
    val weight: Int,
    val muscleGroup: List<Muscle>? = null,
    val equipments: List<Equipment>? = null
) : Exercise, Serializable {

    override fun calorieSpent(): Int {
        TODO("Not yet implemented")
    }
}