package com.kevin.composestudy

import android.app.Application
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class RingtoneViewModel(private val context: Context) : ViewModel() {
    private val _ringtoneNames = mutableStateOf<List<String>>(emptyList())
    private val _ringtoneUris = mutableStateOf<List<Uri>>(emptyList())

    val ringtoneNames: State<List<String>> get() = _ringtoneNames
    val ringtoneUris: State<List<Uri>> get() = _ringtoneUris

    init {
        // Initialize the ringtone list
        loadRingtoneList()
    }

    private fun loadRingtoneList() {
        val ringtoneManager = RingtoneManager(context)
        ringtoneManager.setType(RingtoneManager.TYPE_ALL) // You can choose other types like TYPE_ALARM
        val cursor = ringtoneManager.cursor
        val names = mutableListOf<String>()
        val uris = mutableListOf<Uri>()

        cursor?.let {
            while (it.moveToNext()) {
                val ringtoneUri = ringtoneManager.getRingtoneUri(it.position)
                val ringtone = ringtoneManager.getRingtone(it.position)
                val name = ringtone?.getTitle(context) ?: "Unknown"
                names.add(name)
                uris.add(ringtoneUri)
            }
        }
        _ringtoneNames.value = names
        _ringtoneUris.value = uris
    }

    fun setRingtoneForNotification(ringtoneUri: Uri) {
        // Here you would set the ringtone for the alarm or notification
        // Example: Set it for a notification
        val notificationManager = NotificationManagerCompat.from(context)
        val builder = NotificationCompat.Builder(context, "alarm_channel")
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("闹铃提醒")
            .setContentText("该起床啦！")
            .setSound(ringtoneUri)  // Set the ringtone URI
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(1, builder.build())
    }
}