package com.kevin.composestudy

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kevin.composestudy.ui.theme.Blue
import com.kevin.composestudy.ui.theme.ComposeStudyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.loading.value }
        }
        setContent {
            ComposeApp()
        }
//        setContent {
//            ComposeStudyTheme(darkTheme = false) {
//                // A surface container using the 'background' color from the theme
//                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                    Surface(
//                        modifier = Modifier
//                            .wrapContentHeight()
//                            .padding(horizontal = 10.dp),
//                        color = Color(0xFF2196f3),
//                        shadowElevation = 10.dp,
//                        shape = CircleShape
//                    ) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 10.dp)
//                                .background(color = Color.Green)
//                        ) {
//                            Image(
//                                modifier = Modifier
//                                    .width(30.dp)
//                                    .height(30.dp),
//                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                                contentDescription = "test"
//                            )
//                            Column {
//
//                                Greeting("Android")
//                                Text(text = "你好", color = Color.Cyan)
//                            }
//                        }
//                    }
//                    Button(onClick = {
//                        println("点击button")
//                        startActivity(Intent(this@MainActivity, SecondActivity::class.java))
////                        Toast.makeText(baseContext,"点击button",Toast.LENGTH_SHORT).show()
//                        Toast.makeText(this@MainActivity, "点击button", Toast.LENGTH_SHORT).show()
//                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
//                        Text(text = "按钮")
//                    }
//                    Counter()
//
//                    TestWeight()
//                    TestState()
//                    TestTextField()
//                    CircularProgressIndicator()
//                    LinearProgressIndicator()
//                    CounterWithAnim()
//                    Image(
//                        painter = painterResource(id = R.drawable.test),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .clip(MaterialTheme.shapes.small)
//                            .width(100.dp)
//                            .height(100.dp)
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun TestImageWithBitmap() {
    val bitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_foreground)
    Image(
        bitmap = bitmap,
        contentDescription = "A dog image"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestTextField() {
    var content by remember {
        mutableStateOf("")
    }
    TextField(
        value = content,
        onValueChange = {
            content = it
        },
        placeholder = { Text(text = "place holder") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Red,
            containerColor = Blue,
            cursorColor = Color.Red,
            placeholderColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(10.dp),
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CounterWithAnim() {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    var expand by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedIndex by remember { mutableStateOf(0) }
    val listAnim =
        listOf<String>(
            "slide up",
            "slide down",
            "slide up in slide up out",
            "slide down  slide down out",
            "scale in",
            "fade"
        )
    Card(colors = CardDefaults.cardColors(Color(0xFFFEBBCC)), modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                DoAnimationContent(count, selectedIndex)
                Button(onClick = { count++ }) {
                    Text(text = "increment")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { expand = true }) {
                    Text(text = "切换Anim")
                }
                DropdownMenu(expanded = expand, onDismissRequest = { expand = false }) {
                    listAnim.forEachIndexed { index, s ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = s,
                                    color = if (selectedIndex == index) Color.Red else Color.Black
                                )
                            }, onClick = {
                                selectedIndex = index
                                expand = false
                            })
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
private fun DoAnimationContent(count: Int, selectIndex: Int) {
    when (selectIndex) {
        0 -> {
            AnimatedContent(
                targetState = count,
                label = "slide up",
                transitionSpec = {
                    slideIntoContainer(
                                        towards = SlideDirection.Up,
                                        animationSpec = tween(durationMillis = 500)
                                    ).togetherWith(ExitTransition.None)
                }, contentAlignment = Alignment.Center
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xff91C8E4)
                    )
                )
            }
        }

        1 -> {
            AnimatedContent(
                targetState = count,
                label = "slide down",
                transitionSpec = {
                    slideIntoContainer(
                                        towards = SlideDirection.Down,
                                        animationSpec = tween(durationMillis = 500)
                                    ).togetherWith(ExitTransition.None)
                }, contentAlignment = Alignment.Center
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xff91C8E4)
                    )
                )
            }
        }

        2 -> {
            AnimatedContent(
                targetState = count,
                label = "slide up in slide up out",
                transitionSpec = {
                    slideIntoContainer(
                        towards = SlideDirection.Up,
                        animationSpec = tween(durationMillis = 500)
                    ) togetherWith slideOutOfContainer(
                        towards = SlideDirection.Up,
                        animationSpec = tween(durationMillis = 500)
                    )
                }, contentAlignment = Alignment.Center
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xff91C8E4)
                    )
                )
            }
        }

        3 -> {
            AnimatedContent(
                targetState = count,
                label = "slide down in slide down out",
                transitionSpec = {
                    slideIntoContainer(
                        towards = SlideDirection.Down,
                        animationSpec = tween(durationMillis = 500)
                    ) togetherWith slideOutOfContainer(
                        towards = SlideDirection.Down,
                        animationSpec = tween(durationMillis = 500)
                    )
                }, contentAlignment = Alignment.Center
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xff91C8E4)
                    )
                )
            }
        }

        4 -> {
            AnimatedContent(
                targetState = count,
                label = "scale in",
                transitionSpec = {
                    scaleIn(animationSpec = tween(durationMillis = 500)).togetherWith(ExitTransition.None)
                }, contentAlignment = Alignment.Center
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xff91C8E4)
                    )
                )
            }
        }

        5 -> {
            AnimatedContent(targetState = count, label = "fade", transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = 500)).togetherWith(
                    ExitTransition.None
                )
            }, contentAlignment = Alignment.Center) { targetCount ->
                Text(
                    text = "$targetCount",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xff91C8E4)
                    )
                )
            }
        }

    }

}

@Composable
fun Counter() {
    val count = remember {
        mutableStateOf(0)
    }
    Button(onClick = { count.value++ }) {
        Text(text = "click count ${count.value}")
    }
}

@Composable
fun TestState() {
    val expand = remember {
        mutableStateOf(false)
    }
//    val expandValue = if (expand.value) 64.dp else 36.dp
    val expandValue by animateDpAsState(
        if (expand.value) 100.dp else 36.dp, label = "stateAnimation", animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )


    Button(

        onClick = { expand.value = !expand.value },
        modifier = Modifier.height(expandValue)
    ) {
        Text(text = if (expand.value) "show less" else "show more")
    }
}

@Composable
fun TestWeight() {
//    var shouldOnboardingShow by remember {
//        mutableStateOf(true)
//    }
    //使用rememberSaveable在旋转屏幕时仍会保持已经改变的状态
    var shouldOnboardingShow by rememberSaveable {
        mutableStateOf(true)
    }
    if (shouldOnboardingShow) {
        OnboardingScreen(onClick = { shouldOnboardingShow = false })
    } else {
        Row(
            modifier = Modifier.background(color = Color.Yellow),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "图片"
                )
            }
            ElevatedButton(onClick = { println("hello") }) {
                Text(text = "show more")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 100, heightDp = 100)
@Composable
fun TestWeightPreview() {
    ComposeStudyTheme {
        TestWeight()
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(color = Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello")
        Button(onClick = onClick, modifier = Modifier.padding(vertical = 24.dp)) {
            Text(text = "haha")
        }

    }
}

@Preview(showBackground = true, widthDp = 100, heightDp = 100, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnboardingPreview() {
    ComposeStudyTheme {
        OnboardingScreen(onClick = {})
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Composable
fun GreetingPreview() {
    ComposeStudyTheme() {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeApp() {
    KevinTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            kevinTabRowScreens.find { it.route == currentDestination?.route } ?: Home
        Scaffold(
            bottomBar = {
                KevinTabRow(
                    allScreens = kevinTabRowScreens, onTapSelected = { newScreen ->
//                navController.navigateSingleTopTo(newScreen.route)

                        navController.navigate(route = newScreen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            KevinNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

class MyViewModel : ViewModel() {
    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            // run background task here
            delay(2000)
            _loading.value = false
        }
    }
}