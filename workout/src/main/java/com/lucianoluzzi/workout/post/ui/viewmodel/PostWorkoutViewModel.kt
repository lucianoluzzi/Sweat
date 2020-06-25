package com.lucianoluzzi.workout.post.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lucianoluzzi.workout.post.domain.usecase.GetExercisesUseCase

class PostWorkoutViewModel(getExercisesUseCase: GetExercisesUseCase) : ViewModel() {
    val exercises = liveData {
        emit(getExercisesUseCase.invoke())
    }
}