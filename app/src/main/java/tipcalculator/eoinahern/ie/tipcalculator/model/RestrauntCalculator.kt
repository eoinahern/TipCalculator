package tipcalculator.eoinahern.ie.tipcalculator.model

class RestrauntCalculator {

	fun calculateTip(input : Double, pct : Int) : TipCalculation {

		val tipAmount = ((input / 100.00) * pct).toDouble()
		val total =  tipAmount + input


		return TipCalculation(input, pct, tipAmount, total)
	}
}