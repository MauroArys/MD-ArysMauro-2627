package be.odisee.arysmauroprojectarysmauro.components

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun RecipeDetailScreen(
    modifier: Modifier,
    recipe: Recipe,
    viewModel: RecipeViewModel,
    huidigeGebruiker: String,
    onBack: () -> Unit,

) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = recipe.titel,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 28.sp
        )

        Spacer(Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Door: ${recipe.gebruikerAangemaaktAlias}")
                Text("Likes: ${recipe.likes}")
                Text("Tijd: ${recipe.tijd} minuten")
                Text("Datum: ${recipe.datumAangemaakt}")

                Spacer(Modifier.height(12.dp))

                IconButton(onClick = { viewModel.toggleLike(recipe) }) {
                    Icon(
                        imageVector = if (recipe.gebruikerGeliked == 1)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.Favorite,
                        contentDescription = null,
                        tint = if (recipe.gebruikerGeliked == 1)
                            Color(0xFF8B4513)
                        else
                            MaterialTheme.colorScheme.outline
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("Ingrediënten", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(recipe.ingredienten.replace("\\n", "\n"))

        Spacer(Modifier.height(24.dp))

        Text("Bereiding", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(recipe.stappen.replace("\\n", "\n"))

        Spacer(Modifier.height(32.dp))



        Button(
            onClick = {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Check dit recept: ${recipe.titel}")
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(intent, "Recept delen"))
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Delen via bericht")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Terug")
        }
    }
}
