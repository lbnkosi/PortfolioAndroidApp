package za.co.lbnkosi.portfolio.ui.features.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ActivityHomeBinding
import za.co.lbnkosi.portfolio.notifications.MyFirebaseMessagingService
import za.co.lbnkosi.portfolio.ui.base.BaseActivity
import za.co.lbnkosi.portfolio.ui.features.chat.user.ChatViewModel

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        configureBottomNavigation()
        subscribeToFirebaseTopic()
    }

    override fun onResume() {
        super.onResume()
        if (intent.getStringExtra(MyFirebaseMessagingService.CHAT_NOTIFICATION_EXTRA_NAME) == MyFirebaseMessagingService.CHAT_NOTIFICATION_EXTRA_VALUE) {
            binding.bottomNavigationView.selectedItemId = R.id.chat
        }
    }

    private fun configureBottomNavigation() {
        binding.bottomNavigationView.selectedItemId = R.id.home
        binding.homeFragmentContainerView.isVisible = true
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            binding.homeFragmentContainerView.isVisible = item.itemId == R.id.home
            binding.chatFragmentContainerView.isVisible = item.itemId == R.id.chat
            binding.aboutFragmentContainerView.isVisible = item.itemId == R.id.about
            binding.settingsFragmentContainerView.isVisible = item.itemId == R.id.settings
            false
        }
    }

    private fun subscribeToFirebaseTopic() {
        if (chatViewModel.isSignedIn()) {
            chatViewModel.getSignedInUser()?.email
            FirebaseMessaging.getInstance().subscribeToTopic("/topics/${chatViewModel.getSignedInUser()?.email?.substringBefore("@")}")
        }
    }
}