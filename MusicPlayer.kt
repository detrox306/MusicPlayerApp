package com.example.musicplayer

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MusicPlayer(private val context: Context) {

    private val player: ExoPlayer = ExoPlayer.Builder(context).build()
    private var isPlaying = false

    fun playSong(songUri: Uri) {
        val mediaItem = MediaItem.fromUri(songUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
        isPlaying = true
    }

    fun togglePlayPause() {
        if (isPlaying) {
            player.pause()
        } else {
            player.play()
        }
        isPlaying = !isPlaying
    }

    fun stop() {
        player.stop()
        isPlaying = false
    }

    fun release() {
        player.release()
    }
}
