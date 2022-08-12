package eu.example.mysecondtimerapplication.departuretimer


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import java.time.LocalDate
import java.time.LocalTime
import java.time.Period


@Composable
fun DepartureScreen(viewModel: DepartureViewModel) {

	// need dependency to be able to use this
	// val viewModel = viewModel<DepartureViewModel>()

	val uiTimeState: Triple<Int, Int, Int> by viewModel.departureTimeState.collectAsState()
	val uiDateState: LocalDate by viewModel.departureDateState.collectAsState()
	val uiLocalTimeState: String by viewModel.departureLocalTimeState.collectAsState()


	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.SpaceAround
	) {
		Text(text = "Time from Calendar")
		Text(text = uiTimeState.toString())

		Divider(thickness = 10.dp)
		Text(text = "Date from LocalDate")
		Text(text = uiDateState.toString())

		Divider(thickness = 10.dp)
		Text(text = "time from LocalTime")
		Text(text = uiLocalTimeState)

		Divider(thickness = 10.dp)

		// Using datepicker from github library
		val dialogState = rememberMaterialDialogState()
		MaterialDialog(
			dialogState = dialogState,
			buttons = {
				positiveButton("Ok")
				negativeButton("Cancel")
			}
		) {

			// Trying to emit the value period / countDownDate
			val _countdownDate = MutableStateFlow(LocalDate.now())
			val countDownDate: StateFlow<LocalDate> = _countdownDate
			// ...

			datepicker(
				initialDate = uiDateState,
				title = "Pick departure date"
			) { date ->
				// Do stuff with java.time.LocalDate object which is passed in
				// pass in uiDateState
				val startDate = uiDateState
				val departureDate: LocalDate = date
				val period = Period.between(startDate, departureDate)
				// uiDateState.plusDays(period.days.toLong())
				date.plusDays(period.days.toLong())


				// I maybe have to use onDateChange to make something happend when i change the date
				// Do stuff with java.time.LocalDate object which is passed in

				// I somehow need to set uiDateState to the difference between departure date -
				// and LocalDate.now()
				// I need to use this val in a composable text function and show it to the user

				// val period = Period.between(uiDateState, departureDate)
			}
		}

		// Calling datepicker
		Button(onClick = { dialogState.show() }) {
			Text(text = "Pick date")
		}

		// Divider(thickness = 10.dp)

		// Show the difference between dates after using datepicker
		Text(text = "Time to departure")
		// Can't call period from MaterialDialogScope
		Text(text = uiDateState.toString())
		// Text(text = dialogState.toString())
		Text(text = "I want to show the value of val period here")

		// Test other datepicker

	}
}
