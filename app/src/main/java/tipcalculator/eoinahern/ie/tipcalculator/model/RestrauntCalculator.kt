package tipcalculator.eoinahern.ie.tipcalculator.model

import android.arch.lifecycle.LiveData

class RestrauntCalculator(var tipRepo : TipCalculationRepository = TipCalculationRepository())  {

	fun calculateTip(input : Double, pct : Int) : TipCalculation {

		val tipAmount = ((input / 100.00) * pct).toDouble()
		val total =  tipAmount + input


		return TipCalculation("", input, pct, tipAmount, total)
	}

	public fun saveTipCalculation(tip : TipCalculation) {
		tipRepo.saveTips(tip)
	}

	fun loadTipCalculationByLoaction( location : String ) : TipCalculation? {
        return tipRepo.getSavedTip(location)
	}

	fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
		return tipRepo.loadSavedTipCalculations()
	}
}