package com.lucianoluzzi.workout.feed.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lucianoluzzi.workout.feed.ui.viewModel.FeedViewModel

class FeedFragmentFactory(private val viewModel: FeedViewModel) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {
            FeedFragment::class.java.name -> FeedFragment(viewModel)
            else -> super.instantiate(classLoader, className)
        }
}