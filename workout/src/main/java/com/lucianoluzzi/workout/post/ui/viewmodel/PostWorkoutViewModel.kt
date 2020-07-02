package com.lucianoluzzi.workout.post.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.workout.post.domain.usecase.GetExercisesUseCase
import com.lucianoluzzi.workout.post.ui.uimodel.WorkoutLineModel
import kotlinx.coroutines.launch

class PostWorkoutViewModel(getExercisesUseCase: GetExercisesUseCase) : ViewModel() {

    private val mExercises = MutableLiveData<List<String>>()
    val exercises: LiveData<List<String>> = mExercises

    private val mWorkoutLines: MutableList<WorkoutLineModel> = mutableListOf()
    val workoutLines: List<WorkoutLineModel> = mWorkoutLines

    init {
        viewModelScope.launch {
            mExercises.value = getExercisesUseCase.invoke()
        }
    }

    fun addWorkoutLine(workoutLineModel: WorkoutLineModel) {
        mWorkoutLines.add(workoutLineModel)
    }

    fun removeWorkoutLine(workoutLineModel: WorkoutLineModel) {
        mWorkoutLines.remove(workoutLineModel)
    }
}