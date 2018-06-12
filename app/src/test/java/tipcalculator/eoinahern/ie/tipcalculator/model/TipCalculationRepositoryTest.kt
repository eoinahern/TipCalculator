package tipcalculator.eoinahern.ie.tipcalculator.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

	@get : Rule
	var testRule : TestRule = InstantTaskExecutorRule()


	lateinit var repo: TipCalculationRepository

	@Before
	fun setUp() {
		repo = TipCalculationRepository()
	}


	@Test
	fun saveTip() {
		val tip = TipCalculation("zam zam", 80.00, 10, 8.00, 88.00)

		repo.saveTips(tip)
		assertEquals(tip, repo.getSavedTip(tip.locationName))
	}

	@Test
	fun testLoadSavedTipCalculations() {

		val tip1 = TipCalculation("zam zam", 80.00, 10, 8.00, 88.00)
		val tip2 = TipCalculation("quay coop", 90.00, 10, 9.00, 99.00)

		repo.saveTips(tip1)
		repo.saveTips(tip2)

		repo.loadSavedTipCalculations().observeForever { tipCalcs -> assertEquals(2, tipCalcs?.size) }
	}
}