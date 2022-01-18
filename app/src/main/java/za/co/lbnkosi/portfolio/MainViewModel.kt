package za.co.lbnkosi.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import za.co.lbnkosi.portfolio.domain.model.Portfolio
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: PortfolioUseCase) : ViewModel() {

    val portfolio: LiveData<Portfolio?>
        get() = _portfolio

    private var _portfolio: MutableLiveData<Portfolio?> = MutableLiveData(Portfolio())

    fun fetchPortfolio() {
        viewModelScope.launch {
            useCase.getPortfolio("12345678", "19823HEUBHKBV19UBIOOIKE").collect {
                _portfolio.value = it.data
            }
        }
    }

}