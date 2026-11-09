package be.odisee.arysmauroprojectarysmauro.components.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.R
import be.odisee.arysmauroprojectarysmauro.components.cards.RecipeCard
import be.odisee.arysmauroprojectarysmauro.data.Recipe

@Composable
fun RecipesList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    huidigeGebruiker: String,
    onRecipeClick: (Recipe) -> Unit,
    onLike: (Recipe) -> Unit,
    onDelete: (Recipe) -> Unit
) {
    //zoekterm
    var searchQuery by remember { mutableStateOf("") }

    //geeft het gezochte recept weer
    val filteredRecipes = recipes.filter {
        it.titel.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = modifier) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //geeft een random recept terug
                Text(
                    text = stringResource(R.string.no_inspiration)
                )

                Button(
                    onClick = {
                        val randomRecipe = recipes.randomOrNull()
                        randomRecipe?.let {
                            onRecipeClick(it)
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.surprise_me)
                    )
                }
            }
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = {
                Text(stringResource(R.string.search_all_recipes))
            },
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
                    huidigeGebruiker = huidigeGebruiker,
                    aangemaaktDoor = recipe.gebruikerAangemaaktAlias ?: "",
                    onClick = { onRecipeClick(recipe) },
                    onLike = { onLike(recipe) },
                    onDelete = { onDelete(recipe) }
                )
            }
        }
    }
}