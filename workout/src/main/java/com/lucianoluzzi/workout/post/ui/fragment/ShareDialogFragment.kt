package com.lucianoluzzi.workout.post.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lucianoluzzi.domain.WeightLiftExercise
import com.lucianoluzzi.domain.Workout
import com.lucianoluzzi.utils.DateTimeUtils
import com.lucianoluzzi.workout.databinding.ShareDialogFragmentBinding
import com.lucianoluzzi.workout.post.ui.adapter.ExerciseItemAdapter

class ShareDialogFragment : DialogFragment() {

    private val binding by lazy {
        val inflater = LayoutInflater.from(context)
        ShareDialogFragmentBinding.inflate(inflater)
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
        setUpViews()
    }

    private fun setUpViews() {
        binding.date.text = DateTimeUtils().getDisplayableCurrentDate()

        setWorkoutList()
    }

    private fun setWorkoutList() {
        val workout = requireArguments().getSerializable("exercises") as Workout
        val exercises = (workout.activities as List<WeightLiftExercise>)
        val exerciseAdapter = ExerciseItemAdapter(exercises)
        binding.exerciseList.adapter = exerciseAdapter
    }
}