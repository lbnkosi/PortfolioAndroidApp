package za.co.lbnkosi.portfolio.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.util.dialogs.DialogUtils
import za.co.lbnkosi.portfolio.util.dialogs.LoadingDialog
import za.co.lbnkosi.portfolio.util.sharedPrefs.SharedPreferenceHelper

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private var dialogUtils: DialogUtils? = null

    private var loadingDialog: LoadingDialog? = null

    private var sharedPreferenceHelper: SharedPreferenceHelper = SharedPreferenceHelper()

    fun sharedPrefs(): SharedPreferenceHelper {
        return sharedPreferenceHelper
    }

    fun getDialogUtils(context: Context, isFinishing: Boolean = false): DialogUtils? {
        dialogUtils = DialogUtils(context, isFinishing)
        return dialogUtils
    }

    fun showLoadingDialog(message: String = "") {
        loadingDialog = LoadingDialog.newInstance(message)
        loadingDialog?.show(parentFragmentManager, LoadingDialog::class.java.name)
    }

    fun dismissLoadingDialog() {
        loadingDialog?.dismiss()
    }

}