package com.kevin.composestudy

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.kevin.composestudy.http.Api
import com.kevin.composestudy.http.HttpUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    Column {
        Text("Home")
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val api = HttpUtils.create(Api::class.java)
                Log.d("Hello","Hello")
//                val videoDetail = api.requestLogin("inis.cc", 80,10,"zh-cn");
            }
        }, content = { Text(text = "Hello") })
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Surface(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(horizontal = 10.dp),
                        color = Color(0xFF2196f3),
                        shadowElevation = 10.dp,
                        shape = CircleShape
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                                .background(color = Color.Green)
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "test"
                            )
                            Column {

                                Greeting("Android")
                                Text(text = "你好", color = Color.Cyan)
                            }
                        }
                    }
                    Button(onClick = {
                        println("点击button")

                        context.startActivity(Intent(context, SecondActivity::class.java))
//                        Toast.makeText(baseContext,"点击button",Toast.LENGTH_SHORT).show()
                        Toast.makeText(context, "点击button", Toast.LENGTH_SHORT).show()
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                        Text(text = "按钮")
                    }
                    Counter()

                    TestWeight()
                    TestState()
                    TestTextField()
                    CircularProgressIndicator()
                    LinearProgressIndicator()
                    CounterWithAnim()
                    Image(
                        painter = painterResource(id = R.drawable.test),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .width(100.dp)
                            .height(100.dp)
                    )
                }
    }
}