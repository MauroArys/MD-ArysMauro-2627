package be.odisee.arysmauroprojectarysmauro.components.detail

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.arysmauroprojectarysmauro.R
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel
import coil.compose.AsyncImage

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    recipeId: Int,
    viewModel: RecipeViewModel,
    huidigeGebruiker: String,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    //huidig recept ophalen
    val currentRecipe = uiState.recipes.firstOrNull { it.id == recipeId }
    //wat gebruiker heeft getypt
    var commentText by remember {
        mutableStateOf("")
    }
    //heeft gebruiker al comment?
    val heeftAlComment =
        viewModel.commentsForRecipe.any {
            it.alias.equals(huidigeGebruiker, ignoreCase = true)
        }
    //comment bewerken
    var editingCommentId by remember {
        mutableStateOf<Int?>(null)
    }


    // Voer deze code uit wanneer key verandert!
    LaunchedEffect(recipeId) {
        viewModel.clearLikes();
        viewModel.clearComments();

        viewModel.loadCommentsForRecipe(recipeId);
    }

    //indien recept niet gevonden keert hij terug
    if (currentRecipe == null) {
        onBack()
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // Titel + afbeelding samen als header
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = currentRecipe.titel,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(12.dp))

            if (!currentRecipe.afbeelding.isNullOrBlank()) {
                AsyncImage(
                    model = currentRecipe.afbeelding,
                    contentDescription = currentRecipe.titel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(16.dp))
            }
        }

        // Info‑kaart
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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

                Spacer(Modifier.height(16.dp))

                // Like + "Wie heeft geliked?" samen
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (currentRecipe.gebruikerAangemaaktAlias != huidigeGebruiker) {
                        IconButton(
                            onClick = {
                                if (currentRecipe.gebruikerGeliked == 1) {
                                    viewModel.deleteLike(currentRecipe.id)
                                } else {
                                    viewModel.likeRecipe(currentRecipe.id)
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
                                    Color.Red
                                else
                                    MaterialTheme.colorScheme.outline
                            )
                        }
                    }

                    Button(
                        onClick = { viewModel.loadLikesForRecipe(recipeId) },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513))
                    ) {
                        Text(stringResource(R.string.who_likes))
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Ingrediënten
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(Modifier.height(16.dp))
        Text("Ingrediënten", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(currentRecipe.ingredienten.replace("\\n", "\n"))

        Spacer(Modifier.height(24.dp))

        // Stappen
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(Modifier.height(16.dp))
        Text("Bereiding", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(currentRecipe.stappen.replace("\\n", "\n"))

        Spacer(Modifier.height(32.dp))

        //likes-lijst
        if (viewModel.likesForRecipe.isNotEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = stringResource(R.string.gelike_door),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    viewModel.likesForRecipe.forEach { like ->

                        Text(
                            text = "- ${like.alias}"
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }

        // Comments‑lijst
        if (viewModel.commentsForRecipe.isNotEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = stringResource(R.string.comments),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    viewModel.commentsForRecipe.forEach { comment ->

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = comment.alias,
                                style = MaterialTheme.typography.titleSmall
                            )

                            Spacer(Modifier.height(4.dp))

                            if (comment.alias == huidigeGebruiker) {

                                Row {

                                    IconButton(
                                        onClick = {
                                            commentText = comment.commentaar
                                            editingCommentId = comment.id
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = stringResource(R.string.update_comment)
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            viewModel.deleteComment(recipeId)
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = stringResource(R.string.delete_comment)
                                        )
                                    }
                                }
                            }
                        }

                        Text(
                            text = comment.commentaar
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        HorizontalDivider()
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
        if (currentRecipe.gebruikerAangemaaktAlias != huidigeGebruiker && (!heeftAlComment ||
                    editingCommentId != null))
             {

            OutlinedTextField(
                value = commentText,
                onValueChange = {
                    if (it.length <= 255) {
                        commentText = it
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.comment))
                }
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    if (commentText.isNotBlank()) {

                        if (editingCommentId == null) {

                            viewModel.addComment(
                                currentRecipe.id,
                                commentText
                            )

                        } else {

                            viewModel.updateComment(
                                currentRecipe.id,
                                editingCommentId!!,
                                commentText
                            )

                            editingCommentId = null
                        }

                        commentText = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (editingCommentId == null)
                        stringResource(R.string.place_comment)
                    else
                        stringResource(R.string.update_comment)
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }

        // Knoppen onderaan
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //delen knop
            Button(
                onClick = {
                    val shareText = "Deel dit recept: ${currentRecipe.titel}"
                    val shareTitle = "Recept delen"
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, shareText)
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(intent, shareTitle))
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513))
            ) {
                Text(stringResource(R.string.detail_share))
            }
            //terug knop
            Button(
                onClick = onBack,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(stringResource(R.string.detail_back))
            }
        }
    }
}
