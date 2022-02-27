package za.co.lbnkosi.portfolio.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.util.sharedPrefs.SharedPreferenceHelper

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var sharedPreferenceHelper: SharedPreferenceHelper = SharedPreferenceHelper()

    fun sharedPrefs(): SharedPreferenceHelper {
        return sharedPreferenceHelper
    }

}