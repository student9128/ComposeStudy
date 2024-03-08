package com.kevin.composestudy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

interface KevinDestinations {
    val icon: ImageVector
    val route: String
}

object Home : KevinDestinations {
    override val icon: ImageVector
        get() = Icons.Filled.Home
    override val route: String
        get() = "Home"

}

object ListX : KevinDestinations {
    override val icon: ImageVector = Icons.Filled.List
    override val route: String = "List"
}
object Me:KevinDestinations{
    override val icon: ImageVector
        get() = Icons.Filled.AccountBox
    override val route: String
        get() = "Me"

}
val kevinTabRowScreens = listOf(Home,ListX,Me)