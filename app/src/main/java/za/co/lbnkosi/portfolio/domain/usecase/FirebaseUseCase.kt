package za.co.lbnkosi.portfolio.domain.usecase

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.domain.model.ChatUser
import za.co.lbnkosi.portfolio.domain.model.Resource
import za.co.lbnkosi.portfolio.domain.repository.IFirebaseRepository
import javax.inject.Inject

class FirebaseUseCase @Inject constructor(private val repository: IFirebaseRepository) {

    suspend fun signIn(authModel: AuthModel): Flow<Resource<AuthResult>> {
        return repository.signIn(authModel)
    }

    suspend fun signUp(authModel: AuthModel): Flow<Resource<AuthResult>> {
        return repository.signUp(authModel)
    }

    suspend fun sendMessage(chatModel: ChatModel, uid: String, notificationAddress: String): Flow<Resource<Boolean>> {
        return repository.sendMessage(chatModel, uid, notificationAddress)
    }

    suspend fun fetchMessages(uid: String? = null): Flow<Resource<ArrayList<ChatModel>>> {
        return repository.fetchMessages(uid)
    }

    suspend fun getAllChatUsers(): Flow<Resource<ArrayList<ChatUser>>> {
        return repository.getAllChatUsers()
    }

}