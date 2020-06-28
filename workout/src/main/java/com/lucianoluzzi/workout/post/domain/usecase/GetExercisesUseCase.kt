package com.lucianoluzzi.workout.post.domain.usecase

class GetExercisesUseCase() {

    suspend fun invoke(): List<String> {
        return listOf(
            "Rosca direta",
            "Triceps corda",
            "Elevação frontal",
            "Elevação lateral",
            "Desenvolvimento",
            "Arnold press",
            "Supino",
            "Voador"
        )
    }
}