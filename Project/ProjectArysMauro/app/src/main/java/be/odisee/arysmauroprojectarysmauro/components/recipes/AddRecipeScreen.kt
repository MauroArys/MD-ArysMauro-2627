package be.odisee.arysmauroprojectarysmauro.components.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.R
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun AddRecipeScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: RecipeViewModel
) {
    var titel by remember { mutableStateOf("") }
    var tijd by remember { mutableStateOf("") }
    var ingredienten by remember { mutableStateOf("") }
    var stappen by remember { mutableStateOf("") }

    var titelError by remember { mutableStateOf<String?>(null) }
    var tijdError by remember { mutableStateOf<String?>(null) }
    var stappenError by remember { mutableStateOf<String?>(null) }

    val backendErrors = viewModel.backendErrors

    fun validate(): Boolean {
        titelError = when {
            titel.isBlank() -> "Titel is verplicht"
            titel.length > 32 -> "Titel mag maximaal 32 tekens zijn"
            else -> null
        }

        tijdError = when {
            tijd.isBlank() -> "Tijd is verplicht"
            tijd.toIntOrNull() == null -> "Tijd moet een getal zijn"
            else -> null
        }

        stappenError = if (stappen.isBlank()) "Stappen zijn verplicht" else null

        return titelError == null && tijdError == null && stappenError == null
    }


    if ("titel" in backendErrors) titelError = "Titel ontbreekt volgens de server"
    if ("tijd" in backendErrors) tijdError = "Tijd ontbreekt volgens de server"
    if ("stappen" in backendErrors) stappenError = "Stappen ontbreken volgens de server"

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)

    ) {

        OutlinedTextField(
            value = titel,
            onValueChange = { titel = it; validate() },
            label = { Text(stringResource(R.string.add_recipe_field_title)) },
            isError = titelError != null,
            supportingText = { titelError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = tijd,
            onValueChange = { tijd = it; validate() },
            label = { Text(stringResource(R.string.add_recipe_field_time)) },
            isError = tijdError != null,
            supportingText = { tijdError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
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
            onValueChange = { stappen = it; validate() },
            label = { Text(stringResource(R.string.add_recipe_field_steps)) },
            isError = stappenError != null,
            supportingText = { stappenError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
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
                    if (validate()) {
                        viewModel.addRecipe(
                            titel = titel,
                            ingredienten = ingredienten,
                            stappen = stappen,
                            tijd = tijd.toInt(),
                            afbeelding = null
                        )
                        onBack()
                    }
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

