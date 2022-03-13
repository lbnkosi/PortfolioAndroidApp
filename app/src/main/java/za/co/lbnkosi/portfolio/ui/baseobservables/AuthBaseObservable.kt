package za.co.lbnkosi.portfolio.ui.baseobservables

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import za.co.lbnkosi.portfolio.BR

class AuthBaseObservable(private var _name: String = "", private var _email: String = "", private var _password: String = "") : BaseObservable() {

    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    var email: String
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    var password: String
        @Bindable get() = _password
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }

    fun isSignInRequestValid(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    fun isSignUpRequestValid(): Boolean {
        return name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
    }

}