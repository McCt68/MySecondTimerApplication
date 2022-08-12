package eu.example.mysecondtimerapplication.departuretimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class DepartureViewModel: ViewModel() {

	// State holding time
	private val _departureTimeState = MutableStateFlow(Triple(0, 0, 0))
	val departureTimeState: StateFlow<Triple<Int, Int, Int>> = _departureTimeState

	// State holding date
	private val _departureDateState = MutableStateFlow(LocalDate.now())
	val departureDateState: StateFlow<LocalDate> = _departureDateState

	// State holding LocalTime - Not working
	private val _departureLocalTimeState = MutableStateFlow("")
	val departureLocalTimeState: StateFlow<String> = _departureLocalTimeState

	init {
		getTime()
		getDate()
		getLocalTime()
	}

	// get time with Calendar
	private fun getTime() {
		viewModelScope.launch {
			while (true) {
				val cal = Calendar.getInstance()
				val hour = cal.get(Calendar.HOUR)
				val minute = cal.get(Calendar.MINUTE)
				val second = cal.get(Calendar.SECOND)
				_departureTimeState.emit(Triple(hour, minute, second))
				delay(1000L)
			}
		}
	}

	// Get date
	private fun getDate(){
		viewModelScope.launch {
			while (true){
				val date = LocalDate.now()
				_departureDateState.emit(date)
				delay(1000L)
			}
		}
	}

	// Get time with LocalTime
	private fun getLocalTime(){
		viewModelScope.launch {
			while (true){
				val time = LocalTime.now()
				val formattedTime = time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
				_departureLocalTimeState.emit(formattedTime)
				delay(1000L)
			}
		}
	}


	/// TEsting datepicker

}