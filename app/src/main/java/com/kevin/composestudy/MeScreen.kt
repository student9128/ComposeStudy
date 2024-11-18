package com.kevin.composestudy

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.UnsupportedEncodingException


@Composable
fun MeScreen() {
    val context = LocalContext.current
    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // 用户同意权限请求
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                // 用户拒绝权限请求
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    val useExactAlarmPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // 用户同意权限请求
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                // 用户拒绝权限请求
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
//    val alarmPermissionsLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//            val allGranted = permissions.all { it.value }
//            if (allGranted) {
//                Toast.makeText(context, "All permissions granted", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "Some permissions denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    val ringtoneViewModel: RingtoneViewModel = viewModel(factory = RingtoneViewModelFactory(context))
//    val ringtoneNames = ringtoneViewModel.ringtoneNames.value
//    val ringtoneUris = ringtoneViewModel.ringtoneUris.value
    Column {
        Text("Me")
        Text("Click the button to set an alarm")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val permissionStatus = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                )
                val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity, Manifest.permission.POST_NOTIFICATIONS
                )
                if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
                    if(checkSelfPermission(context,Manifest.permission.USE_EXACT_ALARM)){
                            setRepeatingAlarm(context)

                    }else{
                       if(shouldShowRequestPermissionRational(context,Manifest.permission.USE_EXACT_ALARM)){
                          useExactAlarmPermissionLauncher.launch(Manifest.permission.USE_EXACT_ALARM)
                       } else{
                           val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                           }
                           context.startActivity(intent)
                       }
                    }
//                    if (checkSelfPermissions(context).isEmpty()) {
//                        setRepeatingAlarm(context)
//                    } else {
//                        if (checkShouldShowRequestPermissionRationale(context).isEmpty()) {
//                            alarmPermissionsLauncher.launch(
//                                arrayOf(
//                                    Manifest.permission.USE_EXACT_ALARM
//                                )
//                            )
//
//                        } else {
//                            //
//                            Toast.makeText(context, "Permission Denied All", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//
//                    }
                } else if (permissionStatus == PackageManager.PERMISSION_DENIED && !shouldShowRationale) {
                    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
//                        data = Uri.fromParts("package", context.packageName, null)
                        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                    }
                    context.startActivity(intent)
                } else {
                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)

                }
            } else {
                setRepeatingAlarm(context)
            }
        }) {
            Text("Set Alarm")
        }
        Button(onClick = { cancelAlarm(context) }) {
            Text("Cancel Alarm")
        }
        Button(onClick = {
         getRingtone(context)

        }) {
            Text("获取手机铃声")
        }
//        LazyColumn(modifier = Modifier.height(100.dp)) {
//          items(items = ringtoneNames){item-> Text(text = item)}
//        }
    }
}
 fun getRingtone(context: Context){
     CoroutineScope(Dispatchers.Main).launch {
         withContext(Dispatchers.IO){

         val ringtoneManager = RingtoneManager(context)
         ringtoneManager.setType(RingtoneManager.TYPE_RINGTONE) // You can choose other types like TYPE_ALARM
         val cursor = ringtoneManager.cursor
             cursor?.columnNames.let { it?.map { println("it====$it") } }
             cursor?.let { while (it.moveToNext()){
                val title =  cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
                val id =  cursor.getString(RingtoneManager.ID_COLUMN_INDEX)
                 println("title=$title,id=$id")
             } }
                 }
             }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun checkShouldShowRequestPermissionRationale(context: Context): List<String> {
    val permissionList: MutableList<String> = mutableListOf()
    val permissions = checkSelfPermissions(context)
    if (permissions.isNotEmpty()) {
        for (item in permissions) {
            val shouldShowRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, item)
            println("item=$item,shouldShowRationale=$shouldShowRationale")
            if (!shouldShowRationale) {
                permissionList.add(item)
            }
        }
    }
    return permissionList

}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun checkSelfPermissions(context: Context): List<String> {
    val permissionList: MutableList<String> = mutableListOf()
    val permissions =
        arrayOf(Manifest.permission.USE_EXACT_ALARM)
    for (item in permissions) {
        val permissionStatus = ContextCompat.checkSelfPermission(context, item)
        println("item=$item,permissionStatus=${permissionStatus==PackageManager.PERMISSION_GRANTED}")
        if (permissionStatus == PackageManager.PERMISSION_DENIED) {
            permissionList.add(item)
        }
    }
    return permissionList
}

fun checkSelfPermission(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}
fun shouldShowRequestPermissionRational(context: Context,permission: String):Boolean{
    return ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,permission)
}

fun setRepeatingAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, MyAlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

//    val triggerAtMillis = System.currentTimeMillis() + 10000
//    val intervalMillis = 10000L
//
//    alarmManager.setRepeating(
//        AlarmManager.RTC_WAKEUP,
//        triggerAtMillis,
//        intervalMillis,
//        pendingIntent
//    )
    val triggerAtMillis = System.currentTimeMillis() + 1 * 10 * 1000

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 7)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (AlarmManagerCompat.canScheduleExactAlarms(alarmManager)){
            println("setRepeatingAlarm===true")
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
            triggerAtMillis,
            pendingIntent
        )
        }else{
            println("setRepeatingAlarm===false")
            Toast.makeText(context, "请授予精确调度警报权限", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent)
        }
    } else {
        println("setRepeatingAlarm===<S")
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    }
}
fun cancelAlarm(context: Context){
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, MyAlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    alarmManager.cancel(pendingIntent)
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancel(1)
}
