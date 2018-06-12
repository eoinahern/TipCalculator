package tipcalculator.eoinahern.ie.tipcalculator.viewmodel

import android.app.Application
import tipcalculator.eoinahern.ie.tipcalculator.R
import tipcalculator.eoinahern.ie.tipcalculator.model.RestrauntCalculator
import tipcalculator.eoinahern.ie.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(app: Application, val calculator: RestrauntCalculator = RestrauntCalculator()) : ObservableViewModel(app) {

	private var lastTipCalculated = TipCalculation()

	var inputCheckAmount: String = ""
	var inputTipPercent: String = ""

	val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.tipAmount)
	val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.checkAmount)
	val outputTotalDollarAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.grandTotal)
	val locationName get() = lastTipCalculated.locationName

	init {
		updateAmounts(TipCalculation(checkAmount = 0.00, tipPct = 0, tipAmount = 0.00, grandTotal = 0.00))
	}

	fun saveCurrentTip(name: String) {

		val tip = lastTipCalculated.copy(locationName = name)
		calculator.saveTipCalculation(tip)
		updateAmounts(tip)
	}


	private fun updateAmounts(tpc: TipCalculation) {

		lastTipCalculated = tpc
		notifyChange()
	}

	fun calculateTip() {

		val checkAmount = inputCheckAmount.toDoubleOrNull()
		val tipPercent = inputTipPercent.toIntOrNull()


		if (checkAmount != null && tipPercent != null) {
			updateAmounts(calculator.calculateTip(checkAmount, tipPercent))
		}
	}

}