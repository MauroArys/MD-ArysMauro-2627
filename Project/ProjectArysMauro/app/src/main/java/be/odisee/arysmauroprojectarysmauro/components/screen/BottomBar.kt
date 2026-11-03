package be.odisee.arysmauroprojectarysmauro.components.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import be.odisee.arysmauroprojectarysmauro.navigation.Screen
import be.odisee.arysmauroprojectarysmauro.R

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.All.route) },
            label = { Text(stringResource(R.string.tab_all_recipes)) },
            icon = { Icon(Icons.Default.List, contentDescription = null) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.My.route) },
            label = { Text(stringResource(R.string.tab_my_recipes)) },
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Profile.route) },
            label = { Text(stringResource(R.string.tab_profile)) },
            icon = { Icon(Icons.Default.Person, contentDescription = null) }
        )
    }
}
