package za.co.lbnkosi.portfolio.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import za.co.lbnkosi.portfolio.presentation.common.Constants
import za.co.lbnkosi.portfolio.util.enums.ResourceStatus
import za.co.lbnkosi.portfolio.util.network.ConnectivityStatus.isConnected
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: PortfolioUseCase) : ViewModel() {

    var portfolioSource: Portfolio? = null

    val portfolio: LiveData<Portfolio?>
        get() = _portfolio

    private var _portfolio: MutableLiveData<Portfolio?> = MutableLiveData(Portfolio())

    fun fetchPortfolio() {
        if (isConnected()) {
            viewModelScope.launch {
                useCase.getPortfolio(Constants.KEY, Constants.UID).collect {
                    if (it.resourceStatus == ResourceStatus.SUCCESS) {
                        _portfolio.value = it.data
                        portfolioSource = it.data
                    }
                }
            }
        } else {
            fetchPortfolioFromCache()
        }
    }

    private fun fetchPortfolioFromCache() {
        viewModelScope.launch {
            useCase.getPortfolioFromCache().collect {
                if (it.resourceStatus == ResourceStatus.SUCCESS) {
                    _portfolio.value = it.data
                    portfolioSource = it.data
                }
            }
        }
    }


}