package za.co.lbnkosi.portfolio.data.source.remote.portfolio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse
import za.co.lbnkosi.portfolio.data.entity.DynamicContentEntity
import za.co.lbnkosi.portfolio.data.entity.PortfolioEntity
import za.co.lbnkosi.portfolio.data.service.PortfolioApiService
import za.co.lbnkosi.portfolio.data.source.local.dynamicContent.LocalDynamicContentDataSource
import za.co.lbnkosi.portfolio.data.source.local.portfolio.LocalPortfolioDataSource
import za.co.lbnkosi.portfolio.domain.model.*
import javax.inject.Inject

class PortfolioDataSource @Inject constructor(
    private val portfolioLocalDataSource: LocalPortfolioDataSource,
    private val dynamicContentLocalDataSource: LocalDynamicContentDataSource,
    private val apiService: PortfolioApiService
) {

    suspend fun fetchPortfolio(key: String, uid: String): Flow<Resource<Portfolio>> {
        val response = apiService.fetchPortfolio(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.let { portfolioLocalDataSource.saveCache(PortfolioEntity(portfolio = it)) }
            flow { emit(Resource.success(response.body())) }
        } else {
            if (portfolioLocalDataSource.isCacheAvailable()) {
                flow { emit(Resource.success(portfolioLocalDataSource.fetchCache().portfolio, true)) }
            } else
                flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchDynamicContent(key: String, uid: String): Flow<Resource<DynamicContent>> {
        val response = apiService.fetchDynamicContent(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.let { dynamicContentLocalDataSource.saveCache(DynamicContentEntity(dynamicContent = it)) }
            flow { emit(Resource.success(response.body())) }
        } else {
            if (dynamicContentLocalDataSource.isCacheAvailable()) {
                flow { emit(Resource.success(dynamicContentLocalDataSource.fetchCache().dynamicContent, true)) }
            } else
                flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

}