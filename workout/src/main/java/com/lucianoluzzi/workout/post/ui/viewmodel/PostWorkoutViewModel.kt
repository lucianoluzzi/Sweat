package com.lucianoluzzi.workout.post.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.workout.post.domain.usecase.GetExercisesUseCase
import kotlinx.coroutines.launch

class PostWorkoutViewModel(getExercisesUseCase: GetExercisesUseCase) : ViewModel() {

    private val mExercises = MutableLiveData<List<String>>()
    val exercises: LiveData<List<String>> = mExercises

    init {
        viewModelScope.launch {
            mExercises.value = getExercisesUseCase.invoke()
        }
    }
}