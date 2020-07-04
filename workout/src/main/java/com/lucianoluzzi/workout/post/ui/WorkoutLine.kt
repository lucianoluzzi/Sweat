package com.lucianoluzzi.workout.post.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lucianoluzzi.design.R
import com.lucianoluzzi.utils.doNothing
import com.lucianoluzzi.utils.hide
import com.lucianoluzzi.utils.isVisible
import com.lucianoluzzi.utils.show
import com.lucianoluzzi.workout.post.ui.uimodel.WorkoutLineModel

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
                weight.text?.clear()
                repetitions.text?.clear()
                actionButton.hide(keepSize = true)
            } else {
                weightInputLayout.show()
            }
        }
    }

    private fun setWeightTextChangedListener() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) = doNothing

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                doNothing

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let {
                    val unmaskedText = it.toString().replace(" kg", "")
                    val weightWithUnit = context.getString(R.string.weight_mask, unmaskedText)

                    weight.removeTextChangedListener(this)
                    weight.setText(weightWithUnit)
                    weight.setSelection(weightWithUnit.length - 3)
                    weight.addTextChangedListener(this)

                    onWeightVisibilityOrTextChanged(weight.isVisible(), it.toString())
                }
            }
        }

        weight.addTextChangedListener(textWatcher)
    }

    private fun onWeightVisibilityOrTextChanged(isVisible: Boolean, text: String) {
        if (text.isEmpty() || !isVisible) {
            repetitionsInputLayout.hide(keepSize = true)
            actionButton.hide(keepSize = true)
            repetitions.text?.clear()
        } else {
            repetitionsInputLayout.show()
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

    fun setAutoCompleteList(list: List<String>) {
        val adapter = ArrayAdapter<String>(
            context,
            R.layout.autocomplete_list_item,
            R.id.exercise,
            list
        )
        workoutName.setAdapter(adapter)
    }

    fun setActionClickListener(onClickListener: () -> Unit) {
        actionButton.setOnClickListener {
            setDeleteIcon()
            onClickListener()
        }
    }

    fun setViewsContent(workoutLineModel: WorkoutLineModel, isDeleteIcon: Boolean = true) {
        with(workoutLineModel) {
            workoutName.setText(exerciseName)
            weight.setText(exerciseWeight)
            repetitions.setText(exerciseRepetitions)

            if (isDeleteIcon)
                setDeleteIcon()
        }
    }

    fun getWorkoutLineModel() = WorkoutLineModel(
        exerciseName = workoutName.text.toString(),
        exerciseWeight = weight.text.toString(),
        exerciseRepetitions = repetitions.text.toString()
    )

    private fun setDeleteIcon() {
        actionButton.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_delete_circle,
                context.theme
            )
        )
    }
}