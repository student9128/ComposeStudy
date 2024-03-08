package com.kevin.composestudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kevin.composestudy.ui.theme.Blue
import com.kevin.composestudy.ui.theme.ComposeStudyTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                Surface {
//                    TestLazyColumn()
                    ToolBarWidget()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ToolBarWidget() {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Hello") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Red,
                        titleContentColor = Color.Green
                    ),
                    navigationIcon = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Hello")
                        }
                    }
                )
            },
            content = {
                Column(modifier = Modifier.padding(it)) {
                    Text("hello$it")
                    AsyncImage(
                        model = "https://himg.bdimg.com/sys/portrait/item/public.1.44bfe065.vnuURHaHgFx1gm50oWjxZg?tt=1698029576476",
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.0.dp)
                            .border(BorderStroke(5.0.dp, Color.Red), CircleShape)
//                            .padding(5.0.dp)
                            .clip(CircleShape)
                    )
                    TestLazyColumn()
                }
            }

        )
    }

    @Composable
    fun TestLazyColumn(
        modifier: Modifier = Modifier,
        names: List<String> = List(100) { "$it" }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 4.dp)//保证有padding,滑动的时候还可以延伸占满全屏
        ) {
            items(items = names) { name -> ListContent(title = name) }
        }
    }

    @Composable
    fun ListContent(title: String) {
        var expand by rememberSaveable {
            mutableStateOf(false)
        }
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Blue),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
//            border = BorderStroke(
//                width = 1.dp,
//                brush = Brush.linearGradient(colors = listOf(Color.Red, Color.Green))
//            )
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    Text(text = "Hello")
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
                    )
                    if (expand) {
                        Text(text = "this is Compose expand content\n".repeat(4))
                    }
                }
                IconButton(onClick = { expand = !expand }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (expand) stringResource(
                            id = R.string.showLess
                        ) else stringResource(id = R.string.showMore)
                    )
                }
            }
        }

    }
}