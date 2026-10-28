package be.odisee.arysmauroprojectarysmauro.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import be.odisee.arysmauroprojectarysmauro.R
import be.odisee.arysmauroprojectarysmauro.components.recipes.AddRecipeScreen
import be.odisee.arysmauroprojectarysmauro.components.profile.ProfileScreen
import be.odisee.arysmauroprojectarysmauro.components.detail.RecipeDetailScreen
import be.odisee.arysmauroprojectarysmauro.components.recipes.RecipesList
import be.odisee.arysmauroprojectarysmauro.components.recipes.MyRecipeList
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel
import be.odisee.arysmauroprojectarysmauro.viewmodel.SortType

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
            val uiState by sharedViewModel.uiState.collectAsState()

            Scaffold(
                floatingActionButton = {
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FloatingActionButton(onClick = { sharedViewModel.toggleSort() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.Sort,
                                contentDescription = stringResource(R.string.fab_sort)
                            )
                        }

                        FloatingActionButton(
                            onClick = { navController.navigate(Screen.Add.route) }
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = stringResource(R.string.fab_add_recipe)
                            )
                        }
                    }
                }
            ) { innerPadding ->

                val sortedRecipes =
                    when (uiState.sortType) {
                        SortType.LIKES -> uiState.recipes.sortedByDescending { it.likes }
                        SortType.DATUM -> uiState.recipes.sortedByDescending { it.datumAangemaakt }
                    }

                RecipesList(
                    modifier = Modifier.padding(innerPadding),
                    recipes = sortedRecipes,
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
            val uiState by sharedViewModel.uiState.collectAsState()

            Scaffold(
                floatingActionButton = {
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FloatingActionButton(onClick = { sharedViewModel.toggleSort() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.Sort,
                                contentDescription = stringResource(R.string.fab_sort)
                            )
                        }

                        FloatingActionButton(
                            onClick = { navController.navigate(Screen.Add.route) }
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = stringResource(R.string.fab_add_recipe)
                            )
                        }
                    }
                }
            ) { innerPadding ->

                val myRecipes = uiState.recipes.filter {
                    it.gebruikerAangemaaktAlias == "Mauro"
                }

                MyRecipeList(
                    modifier = Modifier.padding(innerPadding),
                    recipes = myRecipes,
                    onRecipeClick = { recipe ->
                        navController.navigate("${Screen.Details.route}/${recipe.id}")
                    },
                    onLikeToggle = { recipe ->
                        sharedViewModel.toggleLike(recipe)
                    },
                    onDelete = { recipe ->
                        sharedViewModel.deleteRecipe(recipe)
                    },
                    huidigeGebruiker = "Mauro"
                )

            }
        }

        // TAB 2 — Profiel
        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        // Nieuw recept
        composable(Screen.Add.route) {
            AddRecipeScreen(onBack = { navController.popBackStack() })
        }

        // Detailweergave
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
