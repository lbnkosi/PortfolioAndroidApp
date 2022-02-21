package za.co.lbnkosi.portfolio.domain.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.domain.model.Resource

interface IFirebaseRepository {

    suspend fun signIn(authModel: AuthModel): Flow<Resource<AuthResult>>

    suspend fun signUp(authModel: AuthModel): Flow<Resource<AuthResult>>

    suspend fun sendMessage(chatModel: ChatModel): Flow<Resource<Boolean>>

    suspend fun fetchMessages(): Flow<Resource<ArrayList<ChatModel>>>

}