package za.co.lbnkosi.portfolio.ui.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import za.co.lbnkosi.portfolio.data.network.Constants
import za.co.lbnkosi.portfolio.domain.enums.ResourceStatus
import za.co.lbnkosi.portfolio.util.network.ConnectivityStatus.isConnected
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: PortfolioUseCase) : ViewModel() {

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
        if (isConnected()) {
            viewModelScope.launch {
                useCase.getPortfolio(Constants.KEY, Constants.UID).collect {
                    if (it.resourceStatus == ResourceStatus.SUCCESS) {
                        _portfolio.value = it.data
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
            fetchDynamicContentFromCache()
        }
    }

    private fun fetchDynamicContentFromCache() {
        viewModelScope.launch {
            useCase.getDynamicContentFromCache().collect {
                if (it.resourceStatus == ResourceStatus.SUCCESS) {
                    _dynamicContent.value = it.data
                }
            }
        }
    }

}