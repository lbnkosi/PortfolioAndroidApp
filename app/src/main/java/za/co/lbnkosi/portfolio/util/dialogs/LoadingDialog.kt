package za.co.lbnkosi.portfolio.util.dialogs

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import za.co.lbnkosi.portfolio.databinding.DialogFragmentLoadingBinding

class LoadingDialog : DialogFragment() {

    private var message: String? = ""

    private lateinit var binding: DialogFragmentLoadingBinding

    companion object {
        fun newInstance(aMessage: String = "") = LoadingDialog().apply {
            message = aMessage
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogFragmentLoadingBinding.inflate(inflater)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE);
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.messageTextView.text = message
        setLottieFile()
    }

    private fun setLottieFile() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.animation.setAnimation("login_white.json")
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.animation.setAnimation("login_black.json")
            }
        }
    }

}