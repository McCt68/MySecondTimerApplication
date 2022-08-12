package eu.example.mysecondtimerapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import eu.example.mysecondtimerapplication.departuretimer.DepartureScreen
import eu.example.mysecondtimerapplication.departuretimer.DepartureViewModel
import eu.example.mysecondtimerapplication.ui.theme.MySecondTimerApplicationTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MySecondTimerApplicationTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {
					Scaffold(
						topBar = {
							TopAppBar(
								title = {
									Text(
										text = "Departure Timer",
										modifier = Modifier.fillMaxWidth(),
										textAlign = TextAlign.Center
									)
								}
							)
						},
						backgroundColor = Yellow
					) {
						DepartureScreen(viewModel = DepartureViewModel())
					}

				}
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	MySecondTimerApplicationTheme {

	}
}