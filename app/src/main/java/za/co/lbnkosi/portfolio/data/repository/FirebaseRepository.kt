package za.co.lbnkosi.portfolio.data.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import za.co.lbnkosi.portfolio.data.source.remote.firebase.FirebaseDataSource
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.domain.model.ChatUser
import za.co.lbnkosi.portfolio.domain.model.Resource
import za.co.lbnkosi.portfolio.domain.repository.IFirebaseRepository
import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val dataSource: FirebaseDataSource) : IFirebaseRepository {
    override suspend fun signIn(authModel: AuthModel): Flow<Resource<AuthResult>> {
        return dataSource.signIn(authModel).map { resource ->
            resource
        }
    }

    override suspend fun signUp(authModel: AuthModel): Flow<Resource<AuthResult>> {
        return dataSource.signUp(authModel).map { resource ->
            resource
        }
    }

    override suspend fun sendMessage(chatModel: ChatModel, uid: String, notificationAddress: String): Flow<Resource<Boolean>> {
        return dataSource.sendMessage(chatModel, uid, notificationAddress).map { resource ->
            resource
        }
    }

    override suspend fun fetchMessages(uid: String?): Flow<Resource<ArrayList<ChatModel>>> {
        return dataSource.fetchMessages(uid).map { resource ->
            resource
        }
    }

    override suspend fun getAllChatUsers(): Flow<Resource<ArrayList<ChatUser>>> {
        return dataSource.getAllChatUsers().map { resource ->
            resource
        }
    }
}