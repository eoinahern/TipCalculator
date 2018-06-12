package tipcalculator.eoinahern.ie.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import tipcalculator.eoinahern.ie.tipcalculator.R
import tipcalculator.eoinahern.ie.tipcalculator.databinding.ActivityMainBinding
import tipcalculator.eoinahern.ie.tipcalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

	lateinit var binding : ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.vm =ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
		setSupportActionBar(binding.toolbar)
	}

}
