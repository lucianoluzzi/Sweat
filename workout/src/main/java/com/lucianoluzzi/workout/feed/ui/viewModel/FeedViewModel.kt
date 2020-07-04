package com.lucianoluzzi.workout.feed.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.domain.Workout
import com.lucianoluzzi.workout.feed.domain.usecase.RetrieveFeedUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FeedViewModel(useCase: RetrieveFeedUseCase) : ViewModel() {
    private val mWorkouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> = mWorkouts

    init {
        viewModelScope.launch {
            useCase.retrieveFeed().collect {
                mWorkouts.value = it
            }
        }
    }
}