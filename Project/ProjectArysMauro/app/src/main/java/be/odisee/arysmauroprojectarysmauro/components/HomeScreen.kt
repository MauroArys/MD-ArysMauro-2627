package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import be.odisee.arysmauroprojectarysmauro.navigation.Screen
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    selectedTab: Int,
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel = viewModel()
) {
    val recipes = viewModel.recipes
    val sortedRecipes = viewModel.sortedRecipes()

    Scaffold(
        floatingActionButton = {
            if (selectedTab == 0 || selectedTab == 1) {
                Column(
                    modifier = Modifier.padding(end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FloatingActionButton(
                        onClick = { viewModel.toggleSort() },
                        containerColor = MaterialTheme.colorScheme.secondary
                    ) {
                        Icon(Icons.Filled.Sort, contentDescription = null)
                    }

                    FloatingActionButton(
                        onClick = { navController.navigate(Screen.Add.route) },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                }
            }
        }
    ) { innerPadding ->

        when (selectedTab) {
            0 -> RecipesList(
                modifier = modifier.padding(innerPadding),
                recipes = sortedRecipes,
                onRecipeClick = { recipe ->
                    navController.navigate(Screen.Details.route + "/${recipe.id}")
                },
                onLikeToggle = { recipe ->
                    viewModel.toggleLike(recipe)
                }
            )

            1 -> MyRecipeList(
                modifier = modifier.padding(innerPadding),
                recipes = recipes,
                onRecipeClick = { recipe ->
                    navController.navigate(Screen.Details.route + "/${recipe.id}")
                },
                onLikeToggle = { recipe ->
                    viewModel.toggleLike(recipe)
                }
            )

            2 -> ProfileScreen(modifier = modifier.padding(innerPadding))
        }
    }
}



