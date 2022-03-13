package za.co.lbnkosi.portfolio.util.databinding

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, url: String) {
        if (url.isNotEmpty()) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("showHideLoadingIndicator")
    fun showHideLoadingIndicator(constraintLayout: ConstraintLayout, show: HomeViewModel) {
        show.showLoading.observe(constraintLayout.context as AppCompatActivity) {
            constraintLayout.isVisible = it
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun isVisible(view: View, boolean: Boolean) {
        view.isVisible = boolean
    }

}