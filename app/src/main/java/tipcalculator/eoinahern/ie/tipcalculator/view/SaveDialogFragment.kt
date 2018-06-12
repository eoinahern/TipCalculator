package tipcalculator.eoinahern.ie.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import tipcalculator.eoinahern.ie.tipcalculator.R
import java.lang.IllegalStateException

class SaveDialogFragment : DialogFragment() {

	interface OnSaveTipListener {
		fun onSaveTip(name: String)
	}

	private var listener: OnSaveTipListener? = null

	override fun onAttach(context: Context) {

		super.onAttach(context)
		listener = context as? OnSaveTipListener

		if (listener == null)
			throw IllegalStateException()
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


		val dialog = context?.let { ctx ->

			val editText = EditText(ctx)
			editText.id = viewId
			editText.hint = "Enter Location"

			AlertDialog.Builder(ctx).setView(editText)
					.setNegativeButton(R.string.action_cancel, { _, _ -> dialog.cancel() })
					.setPositiveButton(R.string.action_save, { _, _ -> onSave(editText) }).create()
		}

		return dialog!!
	}


	override fun onDetach() {

		super.onDetach()
		listener = null
	}

	fun onSave(editText: EditText) {

		var text = editText.text.toString()
		listener?.onSaveTip(text)
	}

	companion object {
		val viewId = View.generateViewId()
	}

}