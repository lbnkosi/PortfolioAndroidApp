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
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.domain.model.Resource
import za.co.lbnkosi.portfolio.presentation.common.Constants.FIRE_UID
import java.util.*
import kotlin.collections.ArrayList

class FirebaseDataSource {

    private val db = Firebase.firestore

    private lateinit var auth: FirebaseAuth

    suspend fun signUp(authModel: AuthModel): Flow<Resource<AuthResult>> {
        auth = Firebase.auth
        val result = auth.createUserWithEmailAndPassword(authModel.email, authModel.password).await()

        if (result.user != null) {
            result.user?.let {
                db.collection("chat").document(it.uid).set(hashMapOf("name" to authModel.name)).await()
                db.collection("chat").document(it.uid).collection("conversations").document(FIRE_UID).set(ChatModel(message = "Welcome. Send me a message and I'll try my best to respond", date = Date(), uid = FIRE_UID)).await()
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

    suspend fun fetchMessages(): Flow<Resource<ArrayList<ChatModel>>> {
        auth = Firebase.auth
        if (auth.currentUser != null) {
            auth.currentUser?.let {
                val result = db.collection("chat").document(it.uid).collection("conversations").orderBy("date", Query.Direction.ASCENDING).get(Source.DEFAULT).await()
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

    suspend fun sendMessage(chatModel: ChatModel): Flow<Resource<Boolean>> {
        auth = Firebase.auth
        auth.currentUser?.let {
            db.collection("chat").document(it.uid).collection("conversations").document().set(chatModel).await()
            return flow { emit(Resource.success(true)) }
        }
        return flow { emit(Resource.error(Pair("", null), null)) }
    }

}