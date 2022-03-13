package za.co.lbnkosi.portfolio.ui.baseobservables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import za.co.lbnkosi.portfolio.BR

class ChatBaseObservable(private var _message: String = "") : BaseObservable() {

    var message: String
        @Bindable get() = _message
        set(value) {
            _message = value
            notifyPropertyChanged(BR.message)
        }

    fun isRequestValid(): Boolean {
        return message.isNotEmpty()
    }

    fun resetFields() {

    }

}