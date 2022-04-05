package za.co.lbnkosi.portfolio.ui.features.chat.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.domain.model.ChatUser
import za.co.lbnkosi.portfolio.domain.model.Portfolio
import za.co.lbnkosi.portfolio.domain.usecase.FirebaseUseCase
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import za.co.lbnkosi.portfolio.ui.baseobservables.AuthBaseObservable
import za.co.lbnkosi.portfolio.ui.baseobservables.ChatBaseObservable
import za.co.lbnkosi.portfolio.domain.enums.ResourceStatus
import za.co.lbnkosi.portfolio.ui.base.BaseViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class ChatViewModel @Inject constructor(private val useCase: FirebaseUseCase, private val portfolioUseCase: PortfolioUseCase) : BaseViewModel() {

    private var uid: String? = ""

    private var notificationAddress: String = ""

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

    val sendMessage: LiveData<Boolean?>
        get() = _sendMessage

    val signedIn: LiveData<Boolean>
        get() = _signedIn

    val messages: LiveData<ArrayList<ChatModel>>
        get() = _messages

    val chatUsers: LiveData<ArrayList<ChatUser>?>
        get() = _chatUsers

    private var _signedIn: MutableLiveData<Boolean> = MutableLiveData()

    private var _signIn: MutableLiveData<AuthResult?> = MutableLiveData()

    private var _sendMessage: MutableLiveData<Boolean?> = MutableLiveData()

    private var _messages: MutableLiveData<ArrayList<ChatModel>> = MutableLiveData()

    private var _chatUsers: MutableLiveData<ArrayList<ChatUser>?> = MutableLiveData()

    val portfolio: LiveData<Portfolio?>
        get() = _portfolio

    private var _portfolio: MutableLiveData<Portfolio?> = MutableLiveData(Portfolio())

    private fun setSignedIn() {
        _signedIn.value = true
    }

    fun isSignedIn() = auth?.currentUser != null

    fun getSignedInUser() = auth?.currentUser

    fun signUp() {
        if (authBaseObservable.isSignUpRequestValid()) {
            viewModelScope.launch {
                useCase.signUp(AuthModel(name = authBaseObservable.name, email = authBaseObservable.email, password = authBaseObservable.password)).collect {
                    if (it.resourceStatus == ResourceStatus.SUCCESS && it.data?.user != null) {
                        _signIn.value = it.data
                        setSignedIn()
                    } else {
                        _signIn.value = null
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
                        setSignedIn()
                    } else {
                        _signIn.value = null
                    }
                }
            }
        }
    }

    fun sendMessage(aUID: String? = null, aNotificationAddress: String? = null) {
        if (aUID.isNullOrEmpty()) {
            auth?.currentUser?.let {
                uid = it.uid
            }
        } else {
            uid = aUID
        }

        if (aNotificationAddress.isNullOrEmpty()) {
            notificationAddress = "lebogang"
        } else {
            notificationAddress = aNotificationAddress.substringBefore("@")
        }

        if (chatBaseObservable.isRequestValid() && auth?.uid != null) {
            viewModelScope.launch {
                uid?.let { UID ->
                    useCase.sendMessage(ChatModel(message = chatBaseObservable.message, date = Date(), uid = auth!!.uid!!), UID, notificationAddress).collect {
                        _sendMessage.value = it.data
                        chatBaseObservable.message = ""
                    }
                }
            }
        }
    }



    //Only send a UID if the admin is logged in. Else always leave it null or empty
    fun fetchMessages(uid: String? = null) {
        viewModelScope.launch {
            useCase.fetchMessages(uid).collect() {
                if (it.resourceStatus == ResourceStatus.SUCCESS) {
                    it.data?.let { messages ->
                        _messages.value = messages
                    }
                }
            }
        }
    }

    fun messagesSnapShotListener(uid: String? = null) {
        auth?.currentUser?.let { user ->
            db.collection("chat").document(if (!uid.isNullOrEmpty()) uid else user.uid).collection("conversations").orderBy("date", Query.Direction.ASCENDING).addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                val messages = ArrayList<ChatModel>()
                snapshot?.forEach { doc -> messages.add(doc.toObject()) }
                _messages.value = messages
            }
        }
    }

    fun fetchPortfolioFromCache() {
        viewModelScope.launch {
            portfolioUseCase.getPortfolioFromCache(this@ChatViewModel).collect {
                if (it.resourceStatus == ResourceStatus.SUCCESS) {
                    _portfolio.value = it.data
                }
            }
        }
    }

    fun fetchChatUsers() {
        viewModelScope.launch {
            useCase.getAllChatUsers().collect {
                if (it.resourceStatus == ResourceStatus.SUCCESS) _chatUsers.value = it.data
            }
        }
    }

    fun deleteAccount() {
        //TODO Implement Delete Feature
    }

    fun signOut() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic("/topics/${getSignedInUser()?.email?.substringBefore("@")}")
        auth?.signOut()
        _signedIn.value = false
    }

}