package be.odisee.mauroaryslemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.mauroaryslemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonApp()

            }
        }

    }
}

@Composable
fun LemonApp() {

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var squeezesDone by remember { mutableStateOf(0) }

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
            .padding(bottom=8.dp),

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Surface(
                color = Color(255, 235, 59),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Lemonade",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
        when (currentStep) {

            // --- Stap 1: Citroenboom ---
            1 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.lemon_tree),
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(4.dp))
                            .border(2.dp, Color(105, 205, 216), RoundedCornerShape(4.dp))
                            .clickable {
                                squeezeCount = (2..4).random()
                                squeezesDone = 0
                                currentStep = 2
                            }
                    )
                    Text(
                        text = stringResource(R.string.tap_lemon_tree),
                        fontSize = 18.sp
                    )
                }
            }

            // --- Stap 2: Citroen uitknijpen ---
            2 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.lemon),
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(4.dp))
                            .border(2.dp, Color(105, 205, 216), RoundedCornerShape(4.dp))
                            .clickable {
                                squeezesDone++
                                if (squeezesDone >= squeezeCount) {
                                    currentStep = 3
                                }
                            }
                    )
                    Text(
                        text = stringResource(R.string.keep_tapping_lemon),
                        fontSize = 18.sp
                    )
                }
            }

            // --- Stap 3: Lemonade drinken ---
            3 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.glass_of_lemonade),
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(4.dp))
                            .border(2.dp, Color(105, 205, 216), RoundedCornerShape(4.dp))
                            .clickable {
                                currentStep = 4
                            }
                    )
                    Text(
                        text = stringResource(R.string.tap_lemonade),
                        fontSize = 18.sp
                    )
                }
            }

            // --- Stap 4: Leeg glas ---
            4 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.empty_glass),
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(4.dp))
                            .border(2.dp, Color(105, 205, 216), RoundedCornerShape(4.dp))
                            .clickable {
                                currentStep = 1
                            }
                    )
                    Text(
                        text = stringResource(R.string.tap_empty_glass),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}

