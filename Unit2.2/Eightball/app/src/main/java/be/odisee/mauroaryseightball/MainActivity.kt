package be.odisee.mauroaryseightball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.mauroaryseightball.ui.theme.EightballTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EightballTheme {
                MagicBallApp()
            }
        }
    }
}

@Composable
fun MagicBallApp() {

    val answers = listOf(
        stringResource(R.string.result_1),
        stringResource(R.string.result_2),
        stringResource(R.string.result_3),
        stringResource(R.string.result_4),
        stringResource(R.string.result_5),
        stringResource(R.string.result_6),
        stringResource(R.string.result_7),
        stringResource(R.string.result_8),
        stringResource(R.string.result_9),
        stringResource(R.string.result_10),
        stringResource(R.string.result_11),
        stringResource(R.string.result_12),
        stringResource(R.string.result_13),
        stringResource(R.string.result_14),
        stringResource(R.string.result_15),
        stringResource(R.string.result_16),
        stringResource(R.string.result_17),
        stringResource(R.string.result_18),
        stringResource(R.string.result_19),
        stringResource(R.string.result_20)
    )

    var currentAnswer by remember { mutableStateOf("Ask your question...") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.bal),
                contentDescription = "Magic 8 Ball",
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = currentAnswer,
                color = Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(140.dp)
                    .padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                currentAnswer = answers.random()
            }
        ) {
            Text(
                text = stringResource(R.string.get_answer),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MagicBallPreview() {
    MagicBallApp()
}
