package tipcalculator.eoinahern.ie.tipcalculator.model


data class TipCalculation(val locationName : String = "",
						  val checkAmount: Double = 0.00,
						  val tipPct: Int = 0,
						  val tipAmount: Double = 0.00,
						  val grandTotal: Double = 0.00)



