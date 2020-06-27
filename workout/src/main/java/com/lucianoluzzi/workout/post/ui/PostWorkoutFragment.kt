package com.lucianoluzzi.workout.post.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.lucianoluzzi.design.widget.WorkoutLine
import com.lucianoluzzi.workout.databinding.FragmentPostWorkoutBinding
import com.lucianoluzzi.workout.post.ui.viewmodel.PostWorkoutViewModel

class PostWorkoutFragment(private val viewModel: PostWorkoutViewModel) : Fragment() {

    private lateinit var exercisesList: List<String>
    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentPostWorkoutBinding.inflate(inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.exercises.observe(viewLifecycleOwner, Observer {
            exercisesList = it
            addLine()
        })
    }

    private fun addLine() {
        val child = WorkoutLine(requireContext())
        child.setActionClickListener {
            onActionClick(child)
        }
        child.setAutoCompleteList(exercisesList)

        val transition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.explode)
        TransitionManager.beginDelayedTransition(binding.exercisesContainer, transition)

        binding.exercisesContainer.addView(child)
    }

    private fun onActionClick(child: View) {
        val indexOfChild = binding.exercisesContainer.indexOfChild(child)
        if (indexOfChild != binding.exercisesContainer.size - 1)
            removeLine(child)
        else
            addLine()
    }

    private fun removeLine(child: View) {
        val transition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        TransitionManager.beginDelayedTransition(binding.exercisesContainer, transition)

        binding.exercisesContainer.removeView(child)
    }
}