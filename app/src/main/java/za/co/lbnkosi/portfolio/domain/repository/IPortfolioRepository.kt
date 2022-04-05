package za.co.lbnkosi.portfolio.domain.repository

import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.data.network.RemoteErrorEmitter
import za.co.lbnkosi.portfolio.domain.model.*

interface IPortfolioRepository {

    suspend fun getPortfolioFromCache(remoteErrorEmitter: RemoteErrorEmitter): Flow<Resource<Portfolio>>

    suspend fun getPortfolio(remoteErrorEmitter: RemoteErrorEmitter, key: String, uid: String): Flow<Resource<Portfolio?>?>?

    suspend fun getDynamicContent(remoteErrorEmitter: RemoteErrorEmitter, key: String, uid: String): Flow<Resource<DynamicContent?>?>?

    suspend fun getDynamicContentFromCache(remoteErrorEmitter: RemoteErrorEmitter): Flow<Resource<DynamicContent>>
}