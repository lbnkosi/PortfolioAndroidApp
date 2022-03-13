package za.co.lbnkosi.portfolio.util.dialogs

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object DialogUtil {

    fun showDialog(context: Context, title: String? = null, message: String, positiveText: String? = null, positiveAction: DialogInterface.OnClickListener? = null, negativeText: String? = null, negativeAction: DialogInterface.OnClickListener? = null) {
        AlertDialog.Builder(context) //set icon
            .setTitle(title) //set message
            .setMessage(message) //set positive button
            .setPositiveButton(positiveText, positiveAction) //set negative button
            .setNegativeButton(negativeText, negativeAction)
            .show()
    }

}