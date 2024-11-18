package com.kevin.composestudy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService


class MyAlarmReceiver : BroadcastReceiver() {
    val CHANNEL_ID: String = "alarm_channel" // 自定义渠道ID
    val CHANNEL_NAME: String = "闹铃通知"
    val CHANNEL_DESC: String = "闹铃提醒通知"
    override fun onReceive(context: Context, intent: Intent) {
        println("闹铃响起====${System.currentTimeMillis()}")
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "alarm_channel")
            .setContentTitle("Alarm Triggered")
            .setContentText("The alarm went off!")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()
        println("闹铃响起====${System.currentTimeMillis()}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESC
            // 注册渠道
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification)
    }
}