package com.ebookfrenzy.flashbeepshake

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Vibrator

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var vib: Vibrator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val camManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cam = camManager.cameraIdList[0]
        Flash.setOnCheckedChangeListener { buttonView, isChecked ->
            camManager.setTorchMode(cam, isChecked)
        }

        var tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        Beep.setOnCheckedChangeListener { buttonView, isChecked ->

                tone.startTone(ToneGenerator.TONE_DTMF_3, 600)	//play specific tone for 600ms
        }

        vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        Shake.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                vib.vibrate(100000)
            }
            else
            {
                vib.cancel()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
