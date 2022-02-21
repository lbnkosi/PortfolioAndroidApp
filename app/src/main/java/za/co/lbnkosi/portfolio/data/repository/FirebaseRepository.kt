package za.co.lbnkosi.portfolio.data.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import za.co.lbnkosi.portfolio.data.source.FirebaseDataSource
import za.co.lbnkosi.portfolio.domain.model.AuthModel
import za.co.lbnkosi.portfolio.domain.model.ChatModel
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

    override suspend fun sendMessage(chatModel: ChatModel): Flow<Resource<Boolean>> {
        return dataSource.sendMessage(chatModel).map { resource ->
            resource
        }
    }

    override suspend fun fetchMessages(): Flow<Resource<ArrayList<ChatModel>>> {
        return dataSource.fetchMessages().map { resource ->
            resource
        }
    }
}