package tipcalculator.eoinahern.ie.tipcalculator.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TipCalculationTest {

	lateinit var calc: RestrauntCalculator

	@Before
	fun setup() {
		calc = RestrauntCalculator()
	}

	@Test
	fun testCalculateTip() {

		val checkInput = 10.00
		val pct = 25

		val baseCalculation = TipCalculation(checkAmount = checkInput)
		val list = listOf(baseCalculation.copy(tipPct = 25, tipAmount = 2.5, grandTotal = 12.50)
				, baseCalculation.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.50)
				, baseCalculation.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.80))

		list.forEach { assertEquals(it, calc.calculateTip(it.checkAmount, it.tipPct)) }
	}
}