package za.co.lbnkosi.portfolio.presentation.base

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.util.sharedPrefs.SharedPreferenceHelper

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private var sharedPreferenceHelper: SharedPreferenceHelper = SharedPreferenceHelper()

    fun sharedPrefs(): SharedPreferenceHelper {
        return sharedPreferenceHelper
    }

}