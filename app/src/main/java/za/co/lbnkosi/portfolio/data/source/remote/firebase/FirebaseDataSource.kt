package za.co.lbnkosi.portfolio.data.source.remote.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.await
import retrofit2.awaitResponse
import za.co.lbnkosi.portfolio.data.network.Constants.FIRE_UID
import za.co.lbnkosi.portfolio.data.service.FCMService
import za.co.lbnkosi.portfolio.data.source.remote.firebase.Extensions.path
import za.co.lbnkosi.portfolio.domain.model.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FirebaseDataSource @Inject constructor(private val fcmService: FCMService) {

    private val db = Firebase.firestore

    private lateinit var auth: FirebaseAuth

    suspend fun signUp(authModel: AuthModel): Flow<Resource<AuthResult>> {
        auth = Firebase.auth
        val result = auth.createUserWithEmailAndPassword(authModel.email, authModel.password).await()

        if (result.user != null) {
            result.user?.let {
                db.path(it.uid).set(hashMapOf("name" to authModel.name, "uid" to it.uid, "email" to authModel.email)).await()
                db.path(it.uid).collection("conversations").document(FIRE_UID).set(ChatModel(message = "Welcome. Send me a message and I'll try my best to respond", date = Date(), uid = FIRE_UID)).await()
            }
        }

        return if (result.user != null) {
            flow { emit(Resource.success(result)) }
        } else {
            flow { emit(Resource.error(Pair("", null), null)) }
        }
    }

    suspend fun signIn(authModel: AuthModel): Flow<Resource<AuthResult>> {
        auth = Firebase.auth
        val result = auth.signInWithEmailAndPassword(authModel.email, authModel.password).await()
        return if (result.user != null) {
            flow { emit(Resource.success(result)) }
        } else {
            flow { emit(Resource.error(Pair("", null), null)) }
        }
    }

    suspend fun fetchMessages(uid: String? = null): Flow<Resource<ArrayList<ChatModel>>> {
        auth = Firebase.auth
        if (auth.currentUser != null) {
            auth.currentUser?.let {
                val result = db.path(if (!uid.isNullOrEmpty()) uid else it.uid).collection("conversations").orderBy("date", Query.Direction.ASCENDING).get(Source.DEFAULT).await()
                val messages = ArrayList<ChatModel>()
                result?.forEach { doc ->
                    messages.add(doc.toObject())
                }
                return flow { emit(Resource.success(messages)) }
            }
            return flow { Resource.error(Pair("", null), null) }
        } else {
            return flow { emit(Resource.error(Pair("", null), null)) }
        }
    }

    suspend fun sendMessage(chatModel: ChatModel, uid: String, notificationAddress: String): Flow<Resource<Boolean>> {
        auth = Firebase.auth
        auth.currentUser?.let {
            db.path(uid).collection("conversations").document().set(chatModel).await()
            sendNotification(
                Notification(
                    to = "/topics/$notificationAddress",
                    data = NotificationBody(
                        title = "New message from ${auth.currentUser?.email}",
                        message = chatModel.message,
                        key1 = "",
                        key2 = ""
                    )
                )
            )
            return flow { emit(Resource.success(true)) }
        }
        return flow { emit(Resource.error(Pair("", null), null)) }
    }

    suspend fun getAllChatUsers(): Flow<Resource<ArrayList<ChatUser>>> {
        val tasks = db.collection("chat").get().await()
        val list: ArrayList<ChatUser> = arrayListOf()
        for (task in tasks) {
            val user = task.toObject<ChatUser>()
            list.add(user)
        }
        return flow { emit(Resource.success(list)) }
    }

    private suspend fun sendNotification(notification: Notification) {
        fcmService.sendNotification(notification).awaitResponse()
    }

}