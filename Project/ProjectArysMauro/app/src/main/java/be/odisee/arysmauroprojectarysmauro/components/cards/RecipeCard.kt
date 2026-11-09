package be.odisee.arysmauroprojectarysmauro.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    huidigeGebruiker: String,
    aangemaaktDoor: String,
    onClick: () -> Unit,
    onLike: () -> Unit,
    onDelete: (() -> Unit)? = null
) {
    //is dit mijn eigen recept?
    val isEigenRecept =
        aangemaaktDoor.trim().equals(huidigeGebruiker.trim(), ignoreCase = true)

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

                //likes
                Text(
                    text = "${stringResource(R.string.label_likes)} $likes",
                    style = MaterialTheme.typography.bodyMedium
                )
                //datum
                Text(
                    text = "${stringResource(R.string.label_date)} $date",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                if (!isEigenRecept) {
                    IconButton(onClick = onLike) {
                        Icon(
                            imageVector = if (isLiked)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.Favorite,
                            contentDescription = null,
                            tint = if (isLiked)
                                Color.Red
                            else
                                MaterialTheme.colorScheme.outline
                        )
                    }
                }


                //toont vuilbakje indien eigen recept
                if (isEigenRecept && onDelete != null) {
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
