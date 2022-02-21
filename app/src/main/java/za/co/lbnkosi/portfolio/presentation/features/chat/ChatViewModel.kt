package za.co.lbnkosi.portfolio.presentation.features.chat

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.domain.model.Portfolio
import za.co.lbnkosi.portfolio.domain.usecase.FirebaseUseCase
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import za.co.lbnkosi.portfolio.presentation.common.Constants
import za.co.lbnkosi.portfolio.util.baseobservables.AuthBaseObservable
import za.co.lbnkosi.portfolio.util.baseobservables.ChatBaseObservable
import za.co.lbnkosi.portfolio.util.enums.ResourceStatus
import za.co.lbnkosi.portfolio.util.network.ConnectivityStatus.isConnected
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class ChatViewModel @Inject constructor(private val useCase: FirebaseUseCase, private val portfolioUseCase: PortfolioUseCase) : ViewModel() {

    private val db = Firebase.firestore

    private var auth: FirebaseAuth? = null

    var authBaseObservable: AuthBaseObservable = AuthBaseObservable()

    var chatBaseObservable: ChatBaseObservable = ChatBaseObservable()

    init {
        auth = Firebase.auth
        messagesSnapShotListener()
    }

    val signIn: LiveData<AuthResult?>
        get() = _signIn

    val signUp: LiveData<AuthResult?>
        get() = _signUp

    val sendMessage: LiveData<Boolean?>
        get() = _sendMessage

    val signedIn: LiveData<Boolean>
        get() = _signedIn

    val messages: LiveData<ArrayList<ChatModel>>
        get() = _messages

    private var _signedIn: MutableLiveData<Boolean> = MutableLiveData()

    private var _signIn: MutableLiveData<AuthResult?> = MutableLiveData()

    private var _signUp: MutableLiveData<AuthResult?> = MutableLiveData()

    private var _sendMessage: MutableLiveData<Boolean?> = MutableLiveData()

    private var _messages: MutableLiveData<ArrayList<ChatModel>> = MutableLiveData()

    val portfolio: LiveData<Portfolio?>
        get() = _portfolio

    private var _portfolio: MutableLiveData<Portfolio?> = MutableLiveData(Portfolio())

    fun signedIn() {
        _signedIn.value = true
    }

    fun signUp() {
        if (authBaseObservable.isSignUpRequestValid()) {
            viewModelScope.launch {
                useCase.signUp(AuthModel(name = authBaseObservable.name, email = authBaseObservable.email, password = authBaseObservable.password)).collect {
                    if (it.resourceStatus == ResourceStatus.SUCCESS && it.data?.user != null) {
                        _signUp.value = it.data
                        signedIn()
                    } else {
                        _signUp.value = null
                    }
                }
            }
        }
    }

    fun signIn() {
        if (authBaseObservable.isSignInRequestValid()) {
            viewModelScope.launch {
                useCase.signIn(AuthModel(email = authBaseObservable.email, password = authBaseObservable.password)).collect {
                    if (it.resourceStatus == ResourceStatus.SUCCESS && it.data?.user != null) {
                        _signIn.value = it.data
                        signedIn()
                    } else {
                        _signIn.value = null
                    }
                }
            }
        }
    }

    fun sendMessage() {
        if (chatBaseObservable.isRequestValid() && auth?.uid != null) {
            viewModelScope.launch {
                useCase.sendMessage(ChatModel(message = chatBaseObservable.message, date = Date(), uid = auth!!.uid!!)).collect {
                    _sendMessage.value = it.data
                }
            }
        }
    }

    fun isSignedIn(): Boolean {
        return auth?.currentUser != null
    }

    fun fetchMessages() {
        viewModelScope.launch {
            useCase.fetchMessages().collect() {
                if (it.resourceStatus == ResourceStatus.SUCCESS) {
                    it.data?.let { messages ->
                        _messages.value = messages
                    }
                }
            }
        }
    }

    private fun messagesSnapShotListener() {
        auth?.currentUser?.let { user ->
            db.collection("chat").document(user.uid).collection("conversations").orderBy("date", Query.Direction.ASCENDING).addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val messages = ArrayList<ChatModel>()
                snapshot?.forEach { doc ->
                    messages.add(doc.toObject())
                }
                _messages.value = messages
            }
        }
    }

    fun fetchPortfolioFromCache() {
        viewModelScope.launch {
            portfolioUseCase.getPortfolioFromCache().collect {
                if (it.resourceStatus == ResourceStatus.SUCCESS) {
                    _portfolio.value = it.data
                }
            }
        }
    }

}