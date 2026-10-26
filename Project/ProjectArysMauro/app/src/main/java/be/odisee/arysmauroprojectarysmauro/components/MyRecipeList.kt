package be.odisee.arysmauroprojectarysmauro.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.ui.components.RecipeCard

@Composable
fun MyRecipeList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onLikeToggle: (Recipe) -> Unit,
    huidigeGebruiker: String
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredRecipes = recipes.filter {
        it.titel.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = modifier) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Zoeken in mijn recepten") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        LazyColumn {
            items(
                items = filteredRecipes,
                key = { it.id }
            ) { recipe ->

                RecipeCard(
                    title = recipe.titel,
                    likes = recipe.likes,
                    date = recipe.datumAangemaakt,
                    isLiked = recipe.gebruikerGeliked == 1,
                    onClick = { onRecipeClick(recipe) },
                    onLikeToggle = { onLikeToggle(recipe) }
                )
            }
        }
    }
}
