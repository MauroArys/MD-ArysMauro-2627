package be.odisee.arysmauroprojectarysmauro.components.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.arysmauroprojectarysmauro.R

@Composable
fun AddRecipeScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var titel by remember { mutableStateOf("") }
    var tijd by remember { mutableStateOf("") }
    var ingredienten by remember { mutableStateOf("") }
    var stappen by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            stringResource(R.string.add_recipe_title),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 26.sp
        )
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = titel,
            onValueChange = { titel = it },
            label = { Text(stringResource(R.string.add_recipe_field_title)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = tijd,
            onValueChange = { tijd = it },
            label = { Text(stringResource(R.string.add_recipe_field_time)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = ingredienten,
            onValueChange = { ingredienten = it },
            label = { Text(stringResource(R.string.add_recipe_field_ingredients)) },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = stappen,
            onValueChange = { stappen = it },
            label = { Text(stringResource(R.string.add_recipe_field_steps)) },
            modifier = Modifier.fillMaxWidth(),
            minLines = 6
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    onBack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.add_recipe_save))
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = onBack,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.add_recipe_back))
            }
        }
    }
}
