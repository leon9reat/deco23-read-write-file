package com.medialink.deco23readwritefile

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStreamWriter

internal object FileHelper {
    private val tag = FileHelper::class.java.simpleName

    fun writeToFile(fileModel: FileModel, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(
                context.openFileOutput(fileModel.filename.toString(), Context.MODE_PRIVATE)
            )
            outputStreamWriter.write(fileModel.data.toString())
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("error", "file write failed", e)
        }
    }

    fun readFromFile(context: Context, filename: String): FileModel {
        val fileModel = FileModel()

        try {
            val inputStream = context.openFileInput(filename)

            inputStream?.let {
                val receiveString = it.bufferedReader().use(BufferedReader::readText)
                inputStream.close()

                fileModel.data = receiveString
                fileModel.filename = filename
            }
        } catch (e: FileNotFoundException) {
            Log.e("error", "file not found", e)
        } catch (e: IOException) {
            Log.e("error", "cannot read file", e)
        }

        return fileModel
    }
}