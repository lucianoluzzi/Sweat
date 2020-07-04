package com.lucianoluzzi.workout.post.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucianoluzzi.domain.WeightLiftExercise
import com.lucianoluzzi.workout.databinding.ShareExerciseItemBinding

class ExerciseItemAdapter(
    private val exercises: List<WeightLiftExercise>
) : RecyclerView.Adapter<ExerciseItemAdapter.ExerciseItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShareExerciseItemBinding.inflate(inflater)
        return ExerciseItemViewHolder(binding)
    }

    override fun getItemCount(): Int = exercises.size

    override fun onBindViewHolder(holder: ExerciseItemViewHolder, position: Int) {
        holder.bind(exercises[position])
    }

    class ExerciseItemViewHolder(
        private val binding: ShareExerciseItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: WeightLiftExercise) {
            binding.exercise = exercise
        }
    }
}