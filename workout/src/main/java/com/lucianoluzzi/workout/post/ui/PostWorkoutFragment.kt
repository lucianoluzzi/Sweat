package com.lucianoluzzi.workout.post.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.lucianoluzzi.workout.R
import com.lucianoluzzi.workout.databinding.FragmentPostWorkoutBinding

class PostWorkoutFragment : Fragment() {

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
        addLine()
    }

    private fun addLine() {
        val inflater = LayoutInflater.from(requireContext())
        val child = inflater.inflate(R.layout.workout_item_list, binding.exercisesContainer, false)
        val addButton = child.findViewById<ImageView>(R.id.add_button)
        addButton.setOnClickListener {
            val transition = TransitionInflater.from(requireContext())
                .inflateTransition(android.R.transition.explode)
            TransitionManager.beginDelayedTransition(binding.exercisesContainer, transition);

            addLine()
        }

        binding.exercisesContainer.addView(child)
    }
}