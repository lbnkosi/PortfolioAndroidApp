package za.co.lbnkosi.portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import za.co.lbnkosi.portfolio.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.portfolio.observe(this, {
            if (it != null) {
                binding.somethingTextView.text = it.user.name
            }
        })

        binding.fetchButton.setOnClickListener {
            viewModel.fetchPortfolio()
        }
    }
}