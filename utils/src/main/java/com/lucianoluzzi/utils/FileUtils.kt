package com.lucianoluzzi.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

class FileUtils {
    fun createTempFile(context: Context, fileName: String): File {
        val workoutsDirectory = File(context.cacheDir, "workout")
        workoutsDirectory.mkdir()
        return File.createTempFile(fileName, null, workoutsDirectory)
    }

    fun writeBitmapToFile(bitmap: Bitmap, file: File): File {
        val fileOutputStream = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()

        bitmap.recycle()

        return file
    }
}
