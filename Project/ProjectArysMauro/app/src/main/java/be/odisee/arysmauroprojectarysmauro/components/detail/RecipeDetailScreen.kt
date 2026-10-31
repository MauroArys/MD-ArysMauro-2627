package be.odisee.arysmauroprojectarysmauro.components.detail

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.arysmauroprojectarysmauro.R
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    viewModel: RecipeViewModel,
    huidigeGebruiker: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val currentRecipe = viewModel.getRecipeById(recipe.id) ?: recipe

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // Titel
        Text(
            text = currentRecipe.titel,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 28.sp
        )

        Spacer(Modifier.height(16.dp))

        // Info‑kaart
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("${stringResource(R.string.detail_by)} ${currentRecipe.gebruikerAangemaaktAlias}")
                Text("${stringResource(R.string.label_likes)} ${currentRecipe.likes}")
                Text("${stringResource(R.string.detail_time)} ${currentRecipe.tijd} ${stringResource(R.string.detail_minutes)}")
                Text("${stringResource(R.string.label_date)} ${currentRecipe.datumAangemaakt}")

                Spacer(Modifier.height(12.dp))

                IconButton(
                    onClick = {
                        if (currentRecipe.gebruikerGeliked == 1) {
                            viewModel.deleteLike(recipe.id)
                        } else {
                            viewModel.likeRecipe(recipe.id)
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (currentRecipe.gebruikerGeliked == 1)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.Favorite,
                        contentDescription = stringResource(R.string.detail_like_button),
                        tint = if (currentRecipe.gebruikerGeliked == 1)
                            Color(0xFF8B4513)
                        else
                            MaterialTheme.colorScheme.outline
                    )
                }

            }
        }

        Spacer(Modifier.height(24.dp))

        // Ingrediënten
        Text(
            text = stringResource(R.string.detail_ingredients),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(currentRecipe.ingredienten.replace("\\n", "\n"))

        Spacer(Modifier.height(24.dp))

        // Stappen
        Text(
            text = stringResource(R.string.detail_steps),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(currentRecipe.stappen.replace("\\n", "\n"))

        Spacer(Modifier.height(32.dp))

        // Knoppen
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    val shareText = "Deel dit recept: ${currentRecipe.titel}"
                    val shareTitle = "Recept delen"

                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, shareText)
                        type = "text/plain"
                    }

                    context.startActivity(Intent.createChooser(intent, shareTitle))
                }
            ) {
                Text("Delen")
            }

            Button(onClick = onBack) {
                Text(stringResource(R.string.detail_back))
            }
        }
    }
}

