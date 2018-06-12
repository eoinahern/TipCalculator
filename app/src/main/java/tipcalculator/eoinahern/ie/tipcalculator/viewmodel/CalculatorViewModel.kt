package tipcalculator.eoinahern.ie.tipcalculator.viewmodel

import android.app.Application
import tipcalculator.eoinahern.ie.tipcalculator.R
import tipcalculator.eoinahern.ie.tipcalculator.model.RestrauntCalculator
import tipcalculator.eoinahern.ie.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(app : Application, val calculator : RestrauntCalculator = RestrauntCalculator()) : ObservableViewModel(app) {

	var inputCheckAmount : String = ""
	var inputTipPercent : String = ""

	var outputTipAmount  = ""
	var outputCheckAmount = ""
	var outputTotalDollarAmount = ""


	init {
		updateAmounts(TipCalculation(checkAmount = 0.00, tipPct = 0, tipAmount = 0.00, grandTotal =  0.00))
	}


	private fun updateAmounts(tpc : TipCalculation) {

		println("tip amount : ${tpc.tipAmount}")
		println("check amount : ${tpc.checkAmount}")

		outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tpc.tipAmount)
		outputCheckAmount =  getApplication<Application>().getString(R.string.dollar_amount,tpc.checkAmount)
		outputTotalDollarAmount = getApplication<Application>().getString(R.string.dollar_amount,tpc.grandTotal)
	}

	fun calculateTip() {

		val checkAmount = inputCheckAmount.toDoubleOrNull()
		val tipPercent = inputTipPercent.toIntOrNull()


		if(checkAmount != null && tipPercent != null) {
			updateAmounts( calculator.calculateTip(checkAmount, tipPercent))
			notifyChange()
		}
	}

}