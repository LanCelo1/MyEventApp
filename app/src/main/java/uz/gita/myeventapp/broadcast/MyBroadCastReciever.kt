package uz.gita.myeventapp.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import uz.gita.myeventapp.R
import uz.gita.myeventapp.domain.MainRepository

class MyBroadCastReceiver : BroadcastReceiver() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    override fun onReceive(context: Context?, intent: Intent?) {
        val listStates = MainRepository().getState()
        Log.d("TTT", "${intent?.action!!}")
        Log.d("TTT", "${listStates}")

        scope.launch {
            when (intent?.action) {
                Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                    Log.d("TTT","state = ${listStates[0].state}")
                    if (listStates[0].state) {
                        var mediaPlayer = MediaPlayer.create(context, R.raw.airplane_mode_changed)
                        mediaPlayer.start()
                    }
                }
                Intent.ACTION_SCREEN_OFF -> {
                    if (listStates[1].state) {
                        var mediaPlayer = MediaPlayer.create(context, R.raw.screen_off)
                        mediaPlayer.start()
                    }
                }
                Intent.ACTION_SCREEN_ON -> {
                    if (listStates[2].state) {
                        var mediaPlayer = MediaPlayer.create(context, R.raw.screen_on)
                        mediaPlayer.start()
                    }
                }
                Intent.ACTION_POWER_CONNECTED -> {
                    if (listStates[3].state) {
                        var mediaPlayer = MediaPlayer.create(context, R.raw.power_connected)
                        mediaPlayer.start()
                    }
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    if (listStates[4].state) {
                        var mediaPlayer = MediaPlayer.create(context, R.raw.power_disconnected)
                        mediaPlayer.start()
                    }
                }
                Intent.ACTION_BATTERY_OKAY -> {
                    if (listStates[5].state) {
                        var mediaPlayer = MediaPlayer.create(context, R.raw.battery_ok)
                        mediaPlayer.start()
                    }
                }
                Intent.ACTION_BATTERY_LOW -> {
                    if (listStates[6].state) {
                        var mediaPlayer = MediaPlayer.create(context, R.raw.battery_low)
                        mediaPlayer.start()
                    }
                }
            }

        }
    }

}