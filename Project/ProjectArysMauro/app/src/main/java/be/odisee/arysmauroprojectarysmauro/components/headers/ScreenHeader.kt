package be.odisee.arysmauroprojectarysmauro.components.headers

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.ui.theme.ProjectArysMauroTheme
import be.odisee.arysmauroprojectarysmauro.R

@Composable
fun ScreenHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenHeaderPreview() {
    ProjectArysMauroTheme {
        ScreenHeader(stringResource(R.string.tab_all_recipes))
    }
}
