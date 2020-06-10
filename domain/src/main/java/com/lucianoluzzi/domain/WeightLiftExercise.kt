package com.lucianoluzzi.domain

data class WeightLiftExercise(
    override val name: String,
    override val imageUrl: String,
    val repetitions: Int,
    val weight: Int,
    val muscleGroup: List<Muscle>,
    val equipments: List<Equipment>?
) : Exercise {

    override fun calorieSpent(): Int {
        TODO("Not yet implemented")
    }
}