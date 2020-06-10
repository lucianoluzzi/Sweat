package com.lucianoluzzi.domain

data class AerobicExercise(
    override val name: String,
    override val imageUrl: String,
    val duration: Int
) : Exercise {

    override fun calorieSpent(): Int {
        TODO("Not yet implemented")
    }
}