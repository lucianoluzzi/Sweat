package com.lucianoluzzi.workout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.workout.feed.ui.FeedFragment
import com.lucianoluzzi.workout.feed.ui.viewModel.FeedViewModel
import com.lucianoluzzi.workout.post.ui.fragment.PostWorkoutFragment
import com.lucianoluzzi.workout.post.ui.fragment.ShareDialogFragment

class WorkoutFragmentFactory(
    private val profile: Profile,
    private val feedViewModel: FeedViewModel
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {
            FeedFragment::class.java.name -> FeedFragment(
                profile,
                feedViewModel
            )
            PostWorkoutFragment::class.java.name -> PostWorkoutFragment()
            ShareDialogFragment::class.java.name -> ShareDialogFragment()
            else -> super.instantiate(classLoader, className)
        }
}