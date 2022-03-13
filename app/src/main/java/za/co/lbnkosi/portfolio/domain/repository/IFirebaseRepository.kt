package za.co.lbnkosi.portfolio.domain.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.domain.model.ChatUser
import za.co.lbnkosi.portfolio.domain.model.Resource

interface IFirebaseRepository {

    suspend fun signIn(authModel: AuthModel): Flow<Resource<AuthResult>>

    suspend fun signUp(authModel: AuthModel): Flow<Resource<AuthResult>>

    suspend fun sendMessage(chatModel: ChatModel, uid: String, notificationAddress: String): Flow<Resource<Boolean>>

    suspend fun fetchMessages(uid: String? = null): Flow<Resource<ArrayList<ChatModel>>>

    suspend fun getAllChatUsers(): Flow<Resource<ArrayList<ChatUser>>>

}