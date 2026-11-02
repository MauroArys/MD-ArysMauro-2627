package be.odisee.arysmauroprojectarysmauro.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    sharedViewModel: RecipeViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.All.route,
        modifier = modifier
    ) {

        // TAB 0 — Alle recepten
        composable(Screen.All.route) {

            val uiState by sharedViewModel.uiState.collectAsState()
            val huidigeAlias = uiState.huidigeAlias

            LaunchedEffect(navController.currentBackStackEntry?.destination?.route) {
                if (navController.currentBackStackEntry?.destination?.route == Screen.All.route) {
                    sharedViewModel.reload()
                }
            }

            Scaffold(
                floatingActionButton = {
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FloatingActionButton(onClick = { sharedViewModel.reload() }) {
                            Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                        }

                        FloatingActionButton(onClick = { sharedViewModel.toggleSort() }) {
                            Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = stringResource(R.string.fab_sort))
                        }

                        FloatingActionButton(onClick = { navController.navigate(Screen.Add.route) }) {
                            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.fab_add_recipe))
                        }
                    }
                }
            ) { innerPadding ->

                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                    return@Scaffold
                }

                val sortedRecipes =
                    when (uiState.sortType) {
                        SortType.LIKES -> uiState.recipes.sortedByDescending { it.likes }
                        SortType.DATUM -> uiState.recipes.sortedByDescending { it.datumAangemaakt }
                    }

                RecipesList(
                    modifier = Modifier.padding(innerPadding),
                    recipes = sortedRecipes,
                    huidigeGebruiker = huidigeAlias,
                    onRecipeClick = { recipe ->
                        navController.navigate("${Screen.Details.route}/${recipe.id}")
                    },
                    onLike = { recipe ->
                        if (recipe.gebruikerGeliked == 1)
                            sharedViewModel.deleteLike(recipe.id)
                        else
                            sharedViewModel.likeRecipe(recipe.id)
                    },
                    onDelete = { recipe ->
                        sharedViewModel.deleteRecipe(recipe)
                    }
                )
            }
        }

        // TAB 1 — Mijn recepten
        composable(Screen.My.route) {

            val uiState by sharedViewModel.uiState.collectAsState()
            val huidigeAlias = uiState.huidigeAlias

            LaunchedEffect(Unit) {
                sharedViewModel.loadMijnRecepten(295)
            }

            Scaffold(
                floatingActionButton = {
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FloatingActionButton(onClick = { sharedViewModel.toggleSort() }) {
                            Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = stringResource(R.string.fab_sort))
                        }

                        FloatingActionButton(onClick = { navController.navigate(Screen.Add.route) }) {
                            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.fab_add_recipe))
                        }
                    }
                }
            ) { innerPadding ->

                val myRecipes = uiState.mijnRecepten

                MyRecipeList(
                    modifier = Modifier.padding(innerPadding),
                    recipes = myRecipes,
                    huidigeGebruiker = huidigeAlias,
                    onRecipeClick = { recipe ->
                        navController.navigate("${Screen.Details.route}/${recipe.id}")
                    },
                    onLike = { recipe ->
                        if (recipe.gebruikerGeliked == 1)
                            sharedViewModel.deleteLike(recipe.id)
                        else
                            sharedViewModel.likeRecipe(recipe.id)
                    },
                    onDelete = { recipe ->
                        sharedViewModel.deleteRecipe(recipe)
                    }
                )
            }
        }

        // TAB 2 — Profiel
        composable(Screen.Profile.route) {
            val uiState by sharedViewModel.uiState.collectAsState()

            LaunchedEffect(Unit) {
                sharedViewModel.loadGebruiker()
            }

            ProfileScreen(
                viewModel = sharedViewModel,
                uiState = uiState
            )
        }

        // Nieuw recept
        composable(Screen.Add.route) {
            AddRecipeScreen(
                onBack = { navController.popBackStack() },
                viewModel = sharedViewModel
            )
        }

        // Detailweergave
        composable("${Screen.Details.route}/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0

            val uiState by sharedViewModel.uiState.collectAsState()
            val huidigeAlias = uiState.huidigeAlias

            RecipeDetailScreen(
                recipeId = id,
                viewModel = sharedViewModel,
                huidigeGebruiker = huidigeAlias,
                onBack = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    }
}
