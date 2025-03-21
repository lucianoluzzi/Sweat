package com.lucianoluzzi.workout.post.ui.widget

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
import com.lucianoluzzi.utils.show
import com.lucianoluzzi.workout.post.data.PostWorkoutTracker
import com.lucianoluzzi.workout.post.ui.viewmodel.uimodel.WorkoutLineModel

class WorkoutLine(
    context: Context,
    private val tracker: PostWorkoutTracker
) : LinearLayoutCompat(context) {

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
        workoutName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                tracker.trackName(workoutName.text.toString())
            }
        }
        workoutName.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty() || weight.text.isNullOrEmpty() || repetitions.text.isNullOrEmpty())
                actionButton.hide(keepSize = true)
            else
                actionButton.show()
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

                    if (text.isNullOrEmpty() || workoutName.text.isNullOrEmpty() || repetitions.text.isNullOrEmpty())
                        actionButton.hide(keepSize = true)
                    else
                        actionButton.show()
                }
            }
        }
        weight.addTextChangedListener(textWatcher)
        weight.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                tracker.trackWeight(weight.text.toString())
        }
    }

    private fun setRepetitionsTextChangedListener() {
        repetitions.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty() || weight.text.isNullOrEmpty() || workoutName.text.isNullOrEmpty())
                actionButton.hide(keepSize = true)
            else
                actionButton.show()
        }
        repetitions.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                tracker.trackRepetitions(repetitions.text.toString())
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

    fun setViewsContent(workoutLineModel: WorkoutLineModel) {
        with(workoutLineModel) {
            workoutName.setText(exerciseName)
            weight.setText(exerciseWeight)
            repetitions.setText(exerciseRepetitions)
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