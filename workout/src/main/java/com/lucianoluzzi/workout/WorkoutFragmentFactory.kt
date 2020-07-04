package com.lucianoluzzi.workout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lucianoluzzi.workout.feed.ui.FeedFragment
import com.lucianoluzzi.workout.feed.ui.viewModel.FeedViewModel
import com.lucianoluzzi.workout.post.ui.fragment.PostWorkoutFragment

class WorkoutFragmentFactory(
    private val feedViewModel: FeedViewModel
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {
            FeedFragment::class.java.name -> FeedFragment(
                feedViewModel
            )
            PostWorkoutFragment::class.java.name -> PostWorkoutFragment()
            else -> super.instantiate(classLoader, className)
        }
}