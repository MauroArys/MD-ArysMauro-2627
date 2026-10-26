package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.ui.components.RecipeCard

@Composable
fun RecipesList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onLikeToggle: (Recipe) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredRecipes = recipes.filter {
        it.titel.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = modifier) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Zoeken") },
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

