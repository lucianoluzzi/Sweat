package com.lucianoluzzi.domain

interface Exercise {
    val name: String
    val imageUrl: String?

    fun calorieSpent(): Int
}