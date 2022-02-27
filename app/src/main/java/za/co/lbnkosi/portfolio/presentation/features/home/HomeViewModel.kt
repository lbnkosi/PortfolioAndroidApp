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

    val showLoading: LiveData<Boolean>
        get() = _showLoading

    val portfolio: LiveData<Portfolio?>
        get() = _portfolio

    val dynamicContent: LiveData<DynamicContent?>
        get() = _dynamicContent

    private var _showLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private var _portfolio: MutableLiveData<Portfolio?> = MutableLiveData(Portfolio())

    private var _dynamicContent: MutableLiveData<DynamicContent?> = MutableLiveData(DynamicContent())

    fun fetchPortfolio() {
        _showLoading.value = true
        if (isConnected()) {
            viewModelScope.launch {
                useCase.getPortfolio(Constants.KEY, Constants.UID).collect {
                    if (it.resourceStatus == ResourceStatus.SUCCESS) {
                        _portfolio.value = it.data
                        portfolioSource = it.data
                        _showLoading.value = false
                    }
                }
            }
        } else {
            fetchPortfolioFromCache()
        }
    }

    private fun fetchPortfolioFromCache() {
        _showLoading.value = true
        viewModelScope.launch {
            useCase.getPortfolioFromCache().collect {
                if (it.resourceStatus == ResourceStatus.SUCCESS) {
                    _portfolio.value = it.data
                    portfolioSource = it.data
                    _showLoading.value = false
                }
            }
        }
    }

    fun fetchDynamicContent() {
        if (isConnected()) {
            viewModelScope.launch {
                useCase.getDynamicContent(Constants.KEY, Constants.UID).collect {
                    if (it.resourceStatus == ResourceStatus.SUCCESS) {
                        _dynamicContent.value = it.data
                    }
                }
            }
        } else {
            //offline
        }
    }

}