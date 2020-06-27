package com.lucianoluzzi.design.widget

import android.content.Context
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lucianoluzzi.design.R
import com.lucianoluzzi.utils.hide
import com.lucianoluzzi.utils.show

class WorkoutLine(context: Context) : LinearLayoutCompat(context) {

    private val workoutName: AutoCompleteTextView
    private val weight: TextInputEditText
    private val repetitions: TextInputEditText
    private val actionButton: ImageView

    private val workoutInputLayout: TextInputLayout
    private val weightInputLayout: TextInputLayout
    private val repetitionsInputLayout: TextInputLayout

    init {
        inflate(context, R.layout.workout_item_list, this)

        workoutName = findViewById(R.id.exercise_name)
        weight = findViewById(R.id.weight)
        repetitions = findViewById(R.id.repetitions)
        actionButton = findViewById(R.id.action_button)

        workoutInputLayout = findViewById(R.id.exercise_input)
        weightInputLayout = findViewById(R.id.weight_input)
        repetitionsInputLayout = findViewById(R.id.reps_input)

        setWorkoutTextChangedListener()
        setWeightTextChangedListener()
        setRepetitionsTextChangedListener()
    }

    private fun setWorkoutTextChangedListener() {
        workoutName.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                weightInputLayout.hide(keepSize = true)
                repetitionsInputLayout.hide(keepSize = true)
                actionButton.hide(keepSize = true)
            } else {
                weightInputLayout.show()
            }
        }
    }

    private fun setWeightTextChangedListener() {
        weight.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                repetitionsInputLayout.hide(keepSize = true)
                actionButton.hide(keepSize = true)
            } else {
                repetitionsInputLayout.show()
            }
        }
    }

    private fun setRepetitionsTextChangedListener() {
        repetitions.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                actionButton.hide(keepSize = true)
            } else {
                actionButton.show()
            }
        }
    }

    fun setAutoCompleteList(list: String) {

    }

    fun setActionClickListener(onClickListener: OnClickListener) {
        actionButton.setOnClickListener(onClickListener)
    }
}