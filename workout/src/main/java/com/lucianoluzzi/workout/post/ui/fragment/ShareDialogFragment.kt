package com.lucianoluzzi.workout.post.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.lucianoluzzi.domain.WeightLiftExercise
import com.lucianoluzzi.domain.Workout
import com.lucianoluzzi.utils.DateTimeUtils
import com.lucianoluzzi.utils.FileUtils
import com.lucianoluzzi.utils.toBitmap
import com.lucianoluzzi.workout.R
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
        binding.trainOf.text = getString(R.string.share_workout_name, getProfileName())
        binding.cancel.setOnClickListener {
            dismiss()
        }
        binding.share.setOnClickListener {
            share()
        }

        setWorkoutList()
    }

    private fun getProfileName() = requireArguments().getString("profile_name")

    private fun setWorkoutList() {
        val workout = requireArguments().getSerializable("exercises") as Workout
        val exercises = (workout.activities as List<WeightLiftExercise>)
        val exerciseAdapter = ExerciseItemAdapter(exercises)
        binding.exerciseList.adapter = exerciseAdapter
    }

    private fun share() {
        val contentBitmap = binding.contentContainer.toBitmap()
        val fileUtils = FileUtils()
        var tempFile = fileUtils.createTempFile(
            requireContext(),
            "workout${DateTimeUtils().getDateTimeStamp()}"
        )
        tempFile = fileUtils.writeBitmapToFile(contentBitmap, tempFile)
        val uri = FileProvider.getUriForFile(
            requireContext(),
            "com.lucianoluzzi.sweat.provider",
            tempFile
        )
        shareFileToInstagram2(uri)
    }

    private fun shareFileToInstagram(uri: Uri) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/*"
        share.putExtra(Intent.EXTRA_STREAM, uri)
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(share, "Share to"))
    }

    private fun shareFileToInstagram2(uri: Uri) {
        val attributionLinkUrl =
            "https://play.google.com/store/apps/details?id=br.com.unicredmobile"

        val intent = Intent("com.instagram.share.ADD_TO_STORY").apply {
            type = "image/jpeg"
            putExtra("interactive_asset_uri", uri)
            putExtra("content_url", attributionLinkUrl)
            putExtra("top_background_color", "#33FF33")
            putExtra("bottom_background_color", "#FF00FF")
        }

        val activity = requireActivity().apply {
            grantUriPermission(
                "com.instagram.android", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
        val resolveActivity = activity.packageManager.resolveActivity(intent, 0)
        resolveActivity?.let {
            activity.startActivityForResult(intent, 0)
        }
    }
}