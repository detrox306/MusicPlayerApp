package com.example.musicplayer

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class FilePicker(private val context: Context) {

    fun getAudioFiles(): List<Uri> {
        val audioList = mutableListOf<Uri>()
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Audio.Media.DATA)

        val cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndex(MediaStore.Audio.Media.DATA)
            while (it.moveToNext()) {
                val filePath = it.getString(columnIndex)
                audioList.add(Uri.parse(filePath))
            }
        }
        return audioList
    }
}
