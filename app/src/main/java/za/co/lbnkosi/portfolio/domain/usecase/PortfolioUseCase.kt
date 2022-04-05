package za.co.lbnkosi.portfolio.domain.usecase

import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.data.network.RemoteErrorEmitter
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.repository.IPortfolioRepository
import javax.inject.Inject

class PortfolioUseCase @Inject constructor(private val repository: IPortfolioRepository) {

    suspend fun getPortfolioFromCache(remoteErrorEmitter: RemoteErrorEmitter): Flow<Resource<Portfolio>> = repository.getPortfolioFromCache(remoteErrorEmitter)

    suspend fun getPortfolio(remoteErrorEmitter: RemoteErrorEmitter, key: String, uid: String): Flow<Resource<Portfolio?>?>? = repository.getPortfolio(remoteErrorEmitter, key, uid)

    suspend fun getDynamicContentFromCache(remoteErrorEmitter: RemoteErrorEmitter): Flow<Resource<DynamicContent>> = repository.getDynamicContentFromCache(remoteErrorEmitter)

    suspend fun getDynamicContent(remoteErrorEmitter: RemoteErrorEmitter, key: String, uid: String): Flow<Resource<DynamicContent?>?>? = repository.getDynamicContent(remoteErrorEmitter, key, uid)

}