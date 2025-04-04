package com.example.musicplayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var filePicker: FilePicker
    private lateinit var musicPlayer: MusicPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filePicker = FilePicker(this)
        musicPlayer = MusicPlayer(this)

        val listView: ListView = findViewById(R.id.songList)
        val playButton: Button = findViewById(R.id.playButton)
        val stopButton: Button = findViewById(R.id.stopButton)

        // Verificar permisos
        checkPermissions()

        // Mostrar canciones en el ListView
        val songFiles = filePicker.getAudioFiles()
        val adapter = SongAdapter(this, songFiles)
        listView.adapter = adapter

        // Seleccionar y reproducir canción
        listView.setOnItemClickListener { _, _, position, _ ->
            val songUri = songFiles[position]
            musicPlayer.playSong(songUri)
        }

        // Botón Play/Pause
        playButton.setOnClickListener {
            musicPlayer.togglePlayPause()
        }

        // Botón Stop
        stopButton.setOnClickListener {
            musicPlayer.stop()
        }
    }

    // Método para solicitar permisos
    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
