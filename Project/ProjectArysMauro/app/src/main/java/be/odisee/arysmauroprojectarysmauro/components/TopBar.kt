package be.odisee.arysmauroprojectarysmauro.components



import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import be.odisee.arysmauroprojectarysmauro.ui.theme.ProjectArysMauroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(selectedTab: Int) {

    val title = when (selectedTab) {
        0 -> "Alle Recepten"
        1 -> "Mijn Recepten"
        2 -> "Profiel"
        else -> ""
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        colors = androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}
@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    ProjectArysMauroTheme {
        TopBar(selectedTab = 0)
    }
}

