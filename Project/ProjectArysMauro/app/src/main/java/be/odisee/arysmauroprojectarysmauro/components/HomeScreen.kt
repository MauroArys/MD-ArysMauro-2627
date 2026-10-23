package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.navigation.NavHostController
import be.odisee.arysmauroprojectarysmauro.data.dummyRecipes
import be.odisee.arysmauroprojectarysmauro.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavHostController,
    selectedTab: Int,
    modifier: Modifier = Modifier
) {
    var sortType by remember { mutableStateOf("likes") }
    val recipes = remember { mutableStateListOf(*dummyRecipes.toTypedArray()) }

    val sortedRecipes = when (sortType) {
        "likes" -> recipes.sortedByDescending { it.likes }
        else -> recipes.sortedByDescending { it.datumAangemaakt }
    }

    Scaffold(
        floatingActionButton = {
            if (selectedTab == 0 || selectedTab == 1) {
                Column(
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FloatingActionButton(
                        onClick = {
                            sortType = if (sortType == "likes") "datum" else "likes"
                        },
                        containerColor = MaterialTheme.colorScheme.secondary
                    ) {
                        Icon(
                            Icons.Filled.Sort,
                            contentDescription = if (sortType == "likes")
                                "Sorteer op datum"
                            else
                                "Sorteer op likes"
                        )
                    }

                    FloatingActionButton(
                        onClick = { navController.navigate(Screen.Add.route) },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Recept toevoegen")
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
                    val index = recipes.indexOf(recipe)
                    if (index != -1) {
                        val updated = recipe.copy(
                            gebruikerGeliked = if (recipe.gebruikerGeliked == 1) 0 else 1,
                            likes = if (recipe.gebruikerGeliked == 1) recipe.likes - 1 else recipe.likes + 1
                        )
                        recipes[index] = updated
                    }
                }
            )

            1 -> MyRecipeList(
                modifier = modifier.padding(innerPadding),
                recipes = recipes,
                onRecipeClick = { recipe ->
                    navController.navigate(Screen.Details.route + "/${recipe.id}")
                },
                onLikeToggle = { recipe ->
                    val index = recipes.indexOf(recipe)
                    if (index != -1) {
                        val updated = recipe.copy(
                            gebruikerGeliked = if (recipe.gebruikerGeliked == 1) 0 else 1,
                            likes = if (recipe.gebruikerGeliked == 1) recipe.likes - 1 else recipe.likes + 1
                        )
                        recipes[index] = updated
                    }
                }
            )

            2 -> ProfileScreen(
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}


