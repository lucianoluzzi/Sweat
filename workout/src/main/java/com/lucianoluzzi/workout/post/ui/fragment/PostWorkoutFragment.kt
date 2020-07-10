package com.lucianoluzzi.workout.post.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.workout.R
import com.lucianoluzzi.workout.databinding.FragmentPostWorkoutBinding
import com.lucianoluzzi.workout.post.ui.viewmodel.PostWorkoutViewModel
import com.lucianoluzzi.workout.post.ui.viewmodel.uimodel.WorkoutLineModel
import com.lucianoluzzi.workout.post.ui.widget.WorkoutLine
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PostWorkoutFragment : Fragment() {

    private val viewModel by sharedViewModel<PostWorkoutViewModel>()
    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentPostWorkoutBinding.inflate(inflater)
    }

    private lateinit var exercisesList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setToolbar()
        setShareClick()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.workoutLines.isEmpty() && binding.exercisesContainer.size > 1) {
            binding.exercisesContainer.removeAllViews()
            addLine()
        }
    }

    private fun setShareClick() {
        binding.share.setOnClickListener {
            val profile = requireArguments().getSerializable("profile") as Profile

            val action =
                PostWorkoutFragmentDirections.actionPostFragmentToShareDialogFragment(
                    profile.name
                )
            findNavController().navigate(action)
        }
    }

    private fun setToolbar() {
        val navController =
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.exercises.observe(viewLifecycleOwner, Observer {
            exercisesList = it
            setUpLines()
        })
    }

    private fun setUpLines() {
        if (viewModel.workoutLines.isEmpty()) {
            addLine()
        } else {
            for (index in viewModel.workoutLines.indices) {
                addLine(viewModel.workoutLines[index])
                if (index == viewModel.workoutLines.lastIndex) {
                    addLine()
                }
            }
        }
    }

    private fun addLine(workoutLine: WorkoutLineModel? = null) {
        val child =
            WorkoutLine(requireContext())
        child.setActionClickListener {
            onActionClick(child)
        }
        child.setAutoCompleteList(exercisesList)

        workoutLine?.let {
            child.setViewsContent(it)
        }

        val transition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.explode)
        TransitionManager.beginDelayedTransition(binding.exercisesContainer, transition)

        binding.exercisesContainer.addView(child)
    }

    private fun onActionClick(child: WorkoutLine) {
        val indexOfChild = binding.exercisesContainer.indexOfChild(child)

        if (indexOfChild != binding.exercisesContainer.size - 1) {
            removeLineFromPersistency(child.getWorkoutLineModel())
            removeLine(child)
            if (binding.exercisesContainer.size == 1) {
                binding.share.hide()
                changeShareVisibility(false)
            }
        } else {
            saveLineInPersistency(child.getWorkoutLineModel())
            addLine()
            changeShareVisibility(true)
            binding.share.show()
        }
    }

    private fun removeLine(child: View) {
        val transition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        TransitionManager.beginDelayedTransition(binding.exercisesContainer, transition)

        binding.exercisesContainer.removeView(child)
    }

    private fun changeShareVisibility(isShow: Boolean) {
        val transition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.explode)
        TransitionManager.beginDelayedTransition(binding.toolbarContentContainer, transition)

        if (isShow)
            binding.share.show()
        else
            binding.share.hide()
    }

    private fun saveLineInPersistency(workoutLineModel: WorkoutLineModel) =
        viewModel.addWorkoutLine(workoutLineModel)

    private fun removeLineFromPersistency(workoutLineModel: WorkoutLineModel) =
        viewModel.removeWorkoutLine(workoutLineModel)
}