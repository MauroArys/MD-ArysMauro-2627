package be.odisee.arysmauroprojectarysmauro.components.screen

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import be.odisee.arysmauroprojectarysmauro.navigation.Screen
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import be.odisee.arysmauroprojectarysmauro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val title = when (currentRoute) {
        Screen.All.route -> stringResource(R.string.tab_all_recipes)
        Screen.My.route -> stringResource(R.string.tab_my_recipes)
        Screen.Profile.route -> stringResource(R.string.tab_profile)
        Screen.Add.route -> stringResource(R.string.add_recipe_title)
        else -> stringResource(R.string.detail_title)
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
            )
    )
}
