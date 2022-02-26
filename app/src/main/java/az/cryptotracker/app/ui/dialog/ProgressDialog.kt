package az.cryptotracker.app.ui.dialog


import android.app.Activity
import android.app.AlertDialog
import az.cryptotracker.app.R


class ProgressDialog constructor(private val activity: Activity) {

    private var dialog: AlertDialog? = null

    fun startLoading() {
        // adding ALERT Dialog builder object and passing activity as parameter
        val builder = AlertDialog.Builder(activity)

        // layoutinflater object and use activity to get layout inflater
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading, null))
        builder.setCancelable(true)

        dialog = builder.create()
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }


}