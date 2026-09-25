package be.odisee.mauroArysbusinesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.mauroArysbusinesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1))
            .padding(32.dp)
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.photo__1_),
                contentDescription = "Profielfoto",
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.15f))
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Text(
                    text = "Mauro Arys",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Graduaat Programmeren – Odisee",
                    fontSize = 20.sp,
                    color = Color(0xFFBBDEFB)
                )
                Text(
                    text = "mauro.arys@odisee.be",
                    fontSize = 16.sp,
                    color = Color(0xFF90CAF9)
                )
            }
        }

        Divider(
            color = Color.White.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 24.dp)
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {


            Text(
                text = "WPLA",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Ik heb hiervoor een vrijstelling aangezien ik een project heb gemaakt op HoGent.",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            Text(
                text = "WPLB",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Ik ben momenteel aan het solliciteren voor een werkplek bij mij in de buurt waar ik de rol als developer kan opnemen.",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            Text(
                text = "SAP",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Hiervoor ben ik ook nog aan het solliciteren en wil ik eveneens de rol als developer opnemen.",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Left
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCard()
}