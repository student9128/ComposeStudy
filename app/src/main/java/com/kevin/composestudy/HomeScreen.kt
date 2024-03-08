package com.kevin.composestudy

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kevin.composestudy.http.Api
import com.kevin.composestudy.http.HttpUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    Column {
        Text("Home")
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val api = HttpUtils.create(Api::class.java)
                Log.d("Hello","Hello")
//                val videoDetail = api.requestLogin("inis.cc", 80,10,"zh-cn");
            }
        }, content = { Text(text = "Hello") })
    }
}