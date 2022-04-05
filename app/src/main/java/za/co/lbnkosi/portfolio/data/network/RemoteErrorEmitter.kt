package za.co.lbnkosi.portfolio.data.network

import za.co.lbnkosi.portfolio.domain.enums.ErrorType


interface RemoteErrorEmitter {
    fun onError(msg: String)
    fun onError(msg: String?, errorType: ErrorType)
}