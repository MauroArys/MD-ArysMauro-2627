package be.odisee.arysmauroprojectarysmauro.navgraph

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import be.odisee.arysmauroprojectarysmauro.navigation.Screen
import be.odisee.arysmauroprojectarysmauro.components.AddRecipeScreen
import be.odisee.arysmauroprojectarysmauro.components.ProfileScreen
import be.odisee.arysmauroprojectarysmauro.components.RecipeDetailScreen
import be.odisee.arysmauroprojectarysmauro.components.RecipesList
import be.odisee.arysmauroprojectarysmauro.ui.screens.MyRecipeList
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sharedViewModel: RecipeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.All.route,
        modifier = modifier
    ) {

        // TAB 0 — Alle recepten
        composable(Screen.All.route) {

            var sortType by remember { mutableStateOf("likes") }

            Scaffold(
                floatingActionButton = {
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        // Sorteer FAB
                        FloatingActionButton(
                            onClick = {
                                sortType = if (sortType == "likes") "datum" else "likes"
                                sharedViewModel.sortType.value = sortType
                            }
                        ) {
                            Icon(
                                Icons.Filled.Sort,
                                contentDescription = "Sorteer"
                            )
                        }

                        // Add FAB
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

                RecipesList(
                    modifier = Modifier.padding(innerPadding),
                    recipes = sharedViewModel.sortedRecipes(),
                    onRecipeClick = { recipe ->
                        navController.navigate("${Screen.Details.route}/${recipe.id}")
                    },
                    onLikeToggle = { recipe ->
                        sharedViewModel.toggleLike(recipe)
                    }
                )
            }
        }

        // TAB 1 — Mijn recepten
        composable(Screen.My.route) {

            var sortType by remember { mutableStateOf("likes") }

            Scaffold(
                floatingActionButton = {
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        // Sorteer FAB
                        FloatingActionButton(
                            onClick = {
                                sortType = if (sortType == "likes") "datum" else "likes"
                                sharedViewModel.sortType.value = sortType
                            }
                        ) {
                            Icon(
                                Icons.Filled.Sort,
                                contentDescription = "Sorteer"
                            )
                        }

                        // Add FAB
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

                MyRecipeList(
                    modifier = Modifier.padding(innerPadding),
                    recipes = sharedViewModel.recipes.filter {
                        it.gebruikerAangemaaktAlias == "Mauro"
                    },
                    onRecipeClick = { recipe ->
                        navController.navigate("${Screen.Details.route}/${recipe.id}")
                    },
                    onLikeToggle = { recipe ->
                        sharedViewModel.toggleLike(recipe)
                    },
                    huidigeGebruiker = "Mauro"
                )
            }
        }

        // TAB 2 — Profiel
        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        // Add Recipe
        composable(Screen.Add.route) {
            AddRecipeScreen(onBack = { navController.popBackStack() })
        }

        // Details
        composable("${Screen.Details.route}/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            val recipe = sharedViewModel.getRecipeById(id)

            if (recipe == null) {
                navController.popBackStack()
                return@composable
            }

            RecipeDetailScreen(
                recipe = recipe,
                viewModel = sharedViewModel,
                huidigeGebruiker = "Mauro",
                onBack = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    }
}
