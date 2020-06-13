package com.lucianoluzzi.workout.feed.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lucianoluzzi.domain.Workout
import com.lucianoluzzi.workout.feed.domain.usecase.RetrieveFeedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn

class FeedViewModel(useCase: RetrieveFeedUseCase) : ViewModel() {
    private val _workout = MutableLiveData<List<Workout>>().apply {
        listOf<Workout>()
    }

    @ExperimentalCoroutinesApi
    val workouts: LiveData<List<Workout>> = useCase.retrieveFeed().flowOn(Dispatchers.IO).asLiveData()
}