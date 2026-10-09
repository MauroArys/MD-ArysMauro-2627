package be.odisee.mauroarysspaceart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.mauroarysspaceart.ui.theme.SpaceArtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaceArtTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }


    val artworks = listOf(
        Artwork(
            imageRes = R.drawable.cr7,
            title = "The Power of Determination",
            artist = "Cristiano Ronaldo",
            year = "2024",
            description = "Een dynamische afbeelding van CR7 die symbool staat voor kracht, discipline en doorzettingsvermogen."
        ),
        Artwork(
            imageRes = R.drawable.messi,
            title = "The Art of Balance",
            artist = "Lionel Messi",
            year = "2023",
            description = "Een kunstzinnige weergave van Messi in actie, met vloeiende lijnen en zachte kleuren die zijn elegantie tonen."
        ),
        Artwork(
            imageRes = R.drawable.maradonna,
            title = "The Golden Era",
            artist = "Diego Maradona",
            year = "2022",
            description = "Een eerbetoon aan Maradona, met vintage tinten en een nostalgische sfeer die zijn legendarische status benadrukt."
        ),
        Artwork(
            imageRes = R.drawable.stadion,
            title = "Cathedral of Football",
            artist = "Mauro",
            year = "2024",
            description = "Een minimalistische foto van een stadion, symbool voor passie, gemeenschap en de magie van het spel."
        ),
        Artwork(
            imageRes = R.drawable.voetbal,
            title = "The Heart of the Game",
            artist = "Mauro",
            year = "2021",
            description = "Een close‑up van een voetbal op gras, het middelpunt van elke wedstrijd en het hart van de sport."
        ),
        Artwork(
            imageRes = R.drawable.banksi,

            title = "Street Passion",
            artist = "Banksy (inspiratie)",
            year = "2020",
            description = "Een street‑art stijl die voetbal en kunst samenbrengt in een rebelse, expressieve compositie."
        )
    )

    val artwork = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(artwork.imageRes),
            contentDescription = artwork.title,
            modifier = Modifier
                .size(250.dp)
                .border(2.dp, Color.Gray)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = artwork.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )


        Text(
            text = "${artwork.artist} (${artwork.year})",
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(12.dp))


        Text(
            text = artwork.description,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) currentIndex--
                    else currentIndex = artworks.lastIndex
                }
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    if (currentIndex < artworks.lastIndex) currentIndex++
                    else currentIndex = 0
                }
            ) {
                Text("Next")
            }
        }
    }
}


data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String,
    val description: String
)

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    SpaceArtTheme {
        ArtSpaceApp()
    }
}
