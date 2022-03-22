package za.co.lbnkosi.portfolio.ui.base

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.util.sharedPrefs.SharedPreferenceHelper

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var sharedPreferenceHelper: SharedPreferenceHelper = SharedPreferenceHelper()

    fun sharedPrefs(): SharedPreferenceHelper {
        return sharedPreferenceHelper
    }

}