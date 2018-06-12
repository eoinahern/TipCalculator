package tipcalculator.eoinahern.ie.tipcalculator.viewmodel

import android.app.Application
import android.content.Context
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before

import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import tipcalculator.eoinahern.ie.tipcalculator.R
import tipcalculator.eoinahern.ie.tipcalculator.model.RestrauntCalculator
import tipcalculator.eoinahern.ie.tipcalculator.model.TipCalculation

class CalculatorViewModelTest {

	lateinit var calculatorViewModel : CalculatorViewModel

	@Mock
	lateinit var mockCalculator : RestrauntCalculator

	@Mock
	lateinit var mockApp : Application

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		stubResource(0.00, "$0.00")
		calculatorViewModel = CalculatorViewModel(mockApp, mockCalculator)
	}

	private fun stubResource(given : Double , returnStr : String) {
		`when`(mockApp.getString(R.string.dollar_amount, given)).thenReturn(returnStr)

	}

	@Test
	fun calculateTipTest() {
		calculatorViewModel.inputCheckAmount = "10.00"
		calculatorViewModel.inputTipPercent = "15"

		stubResource(10.00, "$10.00")
		stubResource(1.50, "$1.50")
		stubResource(11.50, "$11.50")

		val stub = TipCalculation(10.00,tipAmount = 1.50, grandTotal = 11.50)
		Mockito.`when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)

		calculatorViewModel.calculateTip()

		assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
		assertEquals("$1.50", calculatorViewModel.outputTipAmount)
		assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)

	}

	@Test
	fun calculateTipBadInput() {


		calculatorViewModel.inputTipPercent = ""
		calculatorViewModel.inputCheckAmount = "10.00"

		calculatorViewModel.calculateTip()

		verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
	}
}