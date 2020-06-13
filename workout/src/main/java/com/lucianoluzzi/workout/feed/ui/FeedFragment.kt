package com.lucianoluzzi.workout.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lucianoluzzi.workout.databinding.FragmentFeedBinding
import com.lucianoluzzi.workout.feed.ui.viewModel.FeedViewModel

class FeedFragment(private val viewModel: FeedViewModel) : Fragment() {

    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentFeedBinding.inflate(inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFab()

        viewModel.workouts.observe(viewLifecycleOwner, Observer { workoutList ->
            // TODO: populate feed
        })
        return binding.root
    }

    private fun setFab() {

    }
}