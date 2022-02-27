package za.co.lbnkosi.portfolio.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import za.co.lbnkosi.portfolio.data.source.PortfolioDataSource
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.repository.IPortfolioRepository
import javax.inject.Inject

class PortfolioRepository @Inject constructor(private val dataSource: PortfolioDataSource) : IPortfolioRepository {

    override suspend fun getPortfolio(key: String, uid: String): Flow<Resource<Portfolio>> {
        return dataSource.fetchPortfolio(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun getPortfolioFromCache(): Flow<Resource<Portfolio>> {
        return dataSource.fetchPortfolioFromCache().map { resource ->
            resource
        }
    }

    override suspend fun getDynamicContent(key: String, uid: String): Flow<Resource<DynamicContent>> {
        return dataSource.fetchDynamicContent(key, uid).map { resource ->
            resource
        }
    }

}