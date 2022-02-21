package za.co.lbnkosi.portfolio.viewModelTests

import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import za.co.lbnkosi.portfolio.presentation.features.home.HomeViewModel
import javax.inject.Inject

@Config(manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
class HomeViewModelTest : TestCase() {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject lateinit var useCase: PortfolioUseCase

    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun fetchPortfolioTest_ReturnsSuccess() {


    }

}