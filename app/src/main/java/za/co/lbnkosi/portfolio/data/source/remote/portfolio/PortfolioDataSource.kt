package za.co.lbnkosi.portfolio.data.source.remote.portfolio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.await
import retrofit2.awaitResponse
import za.co.lbnkosi.portfolio.data.entity.DynamicContentEntity
import za.co.lbnkosi.portfolio.data.entity.PortfolioEntity
import za.co.lbnkosi.portfolio.data.network.BaseRemoteRepository
import za.co.lbnkosi.portfolio.data.network.RemoteErrorEmitter
import za.co.lbnkosi.portfolio.data.service.PortfolioApiService
import za.co.lbnkosi.portfolio.data.source.local.dynamicContent.LocalDynamicContentDataSource
import za.co.lbnkosi.portfolio.data.source.local.portfolio.LocalPortfolioDataSource
import za.co.lbnkosi.portfolio.domain.model.*
import javax.inject.Inject

class PortfolioDataSource @Inject constructor(
    private val apiService: PortfolioApiService,
    private val portfolioLocalDataSource: LocalPortfolioDataSource,
    private val dynamicContentLocalDataSource: LocalDynamicContentDataSource
) : BaseRemoteRepository() {

    suspend fun fetchPortfolio(remoteErrorEmitter: RemoteErrorEmitter, key: String, uid: String): Flow<Resource<Portfolio?>?>? {
        val result = safeApiCall(remoteErrorEmitter, portfolioLocalDataSource.isCacheAvailable(), { apiService.fetchPortfolio(key, uid).await() }) { portfolioLocalDataSource.fetchCache().portfolio }
        result?.map {
            if (it?.data != null) portfolioLocalDataSource.saveCache(PortfolioEntity(portfolio = it.data))
        }
        return result
    }

    suspend fun fetchDynamicContent(remoteErrorEmitter: RemoteErrorEmitter, key: String, uid: String): Flow<Resource<DynamicContent?>?>? {
        val result = safeApiCall(remoteErrorEmitter, dynamicContentLocalDataSource.isCacheAvailable(), { apiService.fetchDynamicContent(key, uid).await() }) { dynamicContentLocalDataSource.fetchCache().dynamicContent }
        result?.map {
            if (it?.data != null) dynamicContentLocalDataSource.saveCache(DynamicContentEntity(dynamicContent = it.data))
        }
        return result
    }

}