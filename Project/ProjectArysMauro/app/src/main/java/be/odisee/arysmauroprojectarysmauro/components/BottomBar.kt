package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import be.odisee.arysmauroprojectarysmauro.navigation.Screen

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.All.route) },
            label = { Text("Alle recepten") },
            icon = { Icon(Icons.Default.List, null) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.My.route) },
            label = { Text("Mijn recepten") },
            icon = { Icon(Icons.Default.Favorite, null) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Profile.route) },
            label = { Text("Profiel") },
            icon = { Icon(Icons.Default.Person, null) }
        )
    }
}



