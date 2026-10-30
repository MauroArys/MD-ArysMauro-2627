package be.odisee.arysmauroprojectarysmauro.components.recipes


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.components.cards.RecipeCard
import be.odisee.arysmauroprojectarysmauro.R

@Composable
fun MyRecipeList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onLikeToggle: (Recipe) -> Unit,
    onDelete: (Recipe) -> Unit,
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
            label = { Text(stringResource(R.string.search_my_recipes)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        LazyColumn {
            items(filteredRecipes, key = { it.id }) { recipe ->

                RecipeCard(
                    title = recipe.titel,
                    likes = recipe.likes,
                    date = recipe.datumAangemaakt,
                    isLiked = recipe.gebruikerGeliked == 1,
                    onClick = { onRecipeClick(recipe) },
                    onLikeToggle = { onLikeToggle(recipe) },

                    // Delete-knop tonen
                    showDelete = recipe.gebruikerAangemaaktAlias == huidigeGebruiker,

                    // Delete-actie doorgeven
                    onDelete = { onDelete(recipe) }
                )
            }
        }
    }
}

