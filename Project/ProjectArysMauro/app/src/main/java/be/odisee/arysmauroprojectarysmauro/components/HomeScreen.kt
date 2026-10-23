package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import be.odisee.arysmauroprojectarysmauro.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavHostController,
    selectedTab: Int,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            if (selectedTab == 0 || selectedTab == 1) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Add.route) }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Recept toevoegen"
                    )
                }
            }
        }
    ) { innerPadding ->

        when (selectedTab) {
            0 -> RecipesList(
                modifier = modifier.padding(innerPadding),
                onRecipeClick = { recipe ->
                    navController.navigate(Screen.Details.route + "/${recipe.id}")
                }
            )
            1 -> MyRecipeList(
                modifier = modifier.padding(innerPadding),
                onRecipeClick = { recipe ->
                    navController.navigate(Screen.Details.route + "/${recipe.id}")
                }
            )
            2 -> ProfileScreen(
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}
