package za.co.lbnkosi.portfolio.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.awaitResponse
import za.co.lbnkosi.portfolio.data.db.PortfolioDao
import za.co.lbnkosi.portfolio.data.entity.PortfolioEntity
import za.co.lbnkosi.portfolio.data.service.PortfolioApiService
import za.co.lbnkosi.portfolio.domain.model.*
import javax.inject.Inject

class PortfolioDataSource @Inject constructor(private val portfolioDao: PortfolioDao, private val apiService: PortfolioApiService) {

    suspend fun fetchPortfolio(key: String, uid: String): Flow<Resource<Portfolio>> {
        val response = apiService.fetchPortfolio(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.let { saveCache(PortfolioEntity(portfolio = it)) }
            flow { emit(Resource.success(response.body())) }
        } else {
            if (isCacheAvailable()) {
                flow { emit(Resource.success(fetchCache().portfolio, true)) }
            } else
                flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchPortfolioFromCache(): Flow<Resource<Portfolio>> {
        if (isCacheAvailable()) {
            return flow { emit(Resource.success(fetchCache().portfolio, true)) }
        }
        return flow { emit(Resource.error(Pair("", null), null)) }
    }

    private suspend fun fetchCache(): PortfolioEntity {
        return portfolioDao.getPortfolioEntity()
    }

    private suspend fun isCacheAvailable(): Boolean {
        return portfolioDao.availablePortfolio() > 0
    }

    private suspend fun saveCache(portfolioEntity: PortfolioEntity): PortfolioEntity {
        portfolioDao.savePortfolioEntity(portfolioEntity)
        return portfolioDao.getPortfolioEntity()
    }

    suspend fun fetchDynamicContent(key: String, uid: String): Flow<Resource<DynamicContent>> {
        val response = apiService.fetchDynamicContent(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

}