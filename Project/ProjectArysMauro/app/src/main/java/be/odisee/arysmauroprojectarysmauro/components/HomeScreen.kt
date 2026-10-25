package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import be.odisee.arysmauroprojectarysmauro.navigation.Screen
import be.odisee.arysmauroprojectarysmauro.ui.screens.MyRecipeList
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    selectedTab: Int,
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel
) {
    val recipes = viewModel.recipes
    val sortedRecipes = viewModel.sortedRecipes()
    val huidigeGebruiker = "Mauro"

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            if (selectedTab == 0 || selectedTab == 1) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Zoeken...") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
        },
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

        val filteredAllRecipes = sortedRecipes.filter {
            it.titel.contains(searchQuery, ignoreCase = true)
        }

        val filteredMyRecipes = recipes.filter {
            it.gebruikerAangemaaktAlias == huidigeGebruiker
        }.filter {
            it.titel.contains(searchQuery, ignoreCase = true)
        }

        when (selectedTab) {

            0 -> RecipesList(
                modifier = modifier.padding(
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                recipes = filteredAllRecipes,
                onRecipeClick = { recipe ->
                    navController.navigate(Screen.Details.route + "/${recipe.id}")
                },
                onLikeToggle = { recipe ->
                    viewModel.toggleLike(recipe)
                }
            )


            1 -> MyRecipeList(
                modifier = modifier.padding(
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                recipes = filteredMyRecipes,
                onRecipeClick = { recipe ->
                    navController.navigate(Screen.Details.route + "/${recipe.id}")
                },
                onLikeToggle = { recipe ->
                    viewModel.toggleLike(recipe)
                },
                onDelete = { recipe ->
                    viewModel.deleteRecipe(recipe)
                },
                huidigeGebruiker = huidigeGebruiker
            )


            2 -> ProfileScreen(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

        }
    }
}

