package tipcalculator.eoinahern.ie.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class TipCalculationRepository {


	private var mapOfTips = mutableMapOf<String, TipCalculation>()

	fun saveTips(tipCalc: TipCalculation) {
		mapOfTips[tipCalc.locationName] = tipCalc
	}

	fun getSavedTip( location : String ) : TipCalculation? {
		return mapOfTips[location]
	}

	fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
		val liveData = MutableLiveData<List<TipCalculation>>()
		liveData.value = mapOfTips.values.toList()
		return liveData
	}
}