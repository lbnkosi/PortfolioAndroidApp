package za.co.lbnkosi.portfolio.ui.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.util.dialogs.DialogUtils
import za.co.lbnkosi.portfolio.util.sharedPrefs.SharedPreferenceHelper

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var dialogUtils: DialogUtils? = null

    private var sharedPreferenceHelper: SharedPreferenceHelper = SharedPreferenceHelper()

    fun sharedPrefs(): SharedPreferenceHelper {
        return sharedPreferenceHelper
    }

    fun getDialogUtils(context: Context, isFinishing: Boolean = true): DialogUtils? {
        dialogUtils = DialogUtils(context, isFinishing)
        return dialogUtils
    }

}