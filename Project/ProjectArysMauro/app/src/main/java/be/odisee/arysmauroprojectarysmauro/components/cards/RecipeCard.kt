package be.odisee.arysmauroprojectarysmauro.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.R

@Composable
fun RecipeCard(
    title: String,
    likes: Int,
    date: String,
    isLiked: Boolean,
    onClick: () -> Unit,
    onLike: () -> Unit,
    showDelete: Boolean = false,
    onDelete: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "${stringResource(R.string.label_likes)} $likes",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${stringResource(R.string.label_date)} $date",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column(horizontalAlignment = Alignment.End) {

                IconButton(onClick = onLike) {
                    Icon(
                        imageVector = if (isLiked)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.Favorite,
                        contentDescription = null,
                        tint = if (isLiked)
                            Color(0xFF8B4513)
                        else
                            MaterialTheme.colorScheme.outline
                    )
                }

                if (showDelete && onDelete != null) {
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(R.string.verwijder_recept),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}
