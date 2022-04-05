package za.co.lbnkosi.portfolio.ui.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import za.co.lbnkosi.portfolio.data.network.RemoteErrorEmitter
import za.co.lbnkosi.portfolio.domain.enums.ErrorType
import za.co.lbnkosi.portfolio.domain.enums.ScreenState

abstract class BaseViewModel : ViewModel(), RemoteErrorEmitter {

    val mutableProgress = MutableLiveData<Int>(View.GONE)
    val mutableScreenState = MutableLiveData<ScreenState>().apply { value = ScreenState.RENDER }
    val mutableErrorMessage = MutableLiveData<String>()
    val mutableSuccessMessage = MutableLiveData<String>()
    val mutableErrorType = MutableLiveData<Pair<String?, ErrorType>>()


    override fun onError(msg: String?, errorType: ErrorType) {
        mutableErrorType.postValue(Pair(msg, errorType))
    }

    override fun onError(msg: String) {
        mutableErrorMessage.postValue(msg)
    }

}