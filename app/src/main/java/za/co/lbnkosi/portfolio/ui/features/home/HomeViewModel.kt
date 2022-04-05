package za.co.lbnkosi.portfolio.ui.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import za.co.lbnkosi.portfolio.data.network.Constants
import za.co.lbnkosi.portfolio.domain.enums.ResourceStatus
import za.co.lbnkosi.portfolio.ui.base.BaseViewModel
import za.co.lbnkosi.portfolio.util.network.ConnectivityStatus.isConnected
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: PortfolioUseCase) : BaseViewModel() {

    val showLoading: LiveData<Boolean>
        get() = _showLoading

    val portfolio: LiveData<Portfolio?>
        get() = _portfolio

    val dynamicContent: LiveData<DynamicContent?>
        get() = _dynamicContent

    private var _showLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private var _portfolio: MutableLiveData<Portfolio?> = MutableLiveData(Portfolio())

    private var _dynamicContent: MutableLiveData<DynamicContent?> = MutableLiveData(DynamicContent())

    //This will fetch the portfolio data from the remote data source. If the call fails and there is cached data available, it will return the cached data
    fun fetchPortfolio() {
        viewModelScope.launch {
            useCase.getPortfolio(this@HomeViewModel, Constants.KEY, Constants.UID)?.collect {
                if (it?.resourceStatus == ResourceStatus.SUCCESS) {
                    _portfolio.value = it.data
                }
            }
        }
    }

    //This will fetch the dynamic content data from the remote data source. If the call fails and there is cached data available, it will return the cached data
    fun fetchDynamicContent() {
        viewModelScope.launch {
            useCase.getDynamicContent(this@HomeViewModel, Constants.KEY, Constants.UID)?.collect {
                if (it?.resourceStatus == ResourceStatus.SUCCESS) {
                    _dynamicContent.value = it.data
                }
            }
        }
    }
}