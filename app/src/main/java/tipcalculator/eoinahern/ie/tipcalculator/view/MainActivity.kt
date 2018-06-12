package tipcalculator.eoinahern.ie.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import tipcalculator.eoinahern.ie.tipcalculator.R
import tipcalculator.eoinahern.ie.tipcalculator.databinding.ActivityMainBinding
import tipcalculator.eoinahern.ie.tipcalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity(), SaveDialogFragment.OnSaveTipListener {

	lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
		setSupportActionBar(binding.toolbar)
	}

	override fun onSaveTip(name: String) {
		binding.vm?.saveCurrentTip(name)
		Snackbar.make(binding.root, "saved!! $name", Snackbar.LENGTH_LONG).show()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.save_menu, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {

		return when (item?.itemId) {
			R.id.action_save -> {
				showSaveDialog()
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	fun showSaveDialog() {

		val saveFrag = SaveDialogFragment()
		saveFrag.show(supportFragmentManager, "showDialog")
	}
}
