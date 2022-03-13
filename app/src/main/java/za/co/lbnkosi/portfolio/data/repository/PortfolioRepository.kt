package za.co.lbnkosi.portfolio.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import za.co.lbnkosi.portfolio.data.source.local.dynamicContent.LocalDynamicContentDataSource
import za.co.lbnkosi.portfolio.data.source.local.portfolio.LocalPortfolioDataSource
import za.co.lbnkosi.portfolio.data.source.remote.portfolio.PortfolioDataSource
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.repository.IPortfolioRepository
import javax.inject.Inject

class PortfolioRepository @Inject constructor(private val dataSource: PortfolioDataSource, private val portfolioLocalDataSource: LocalPortfolioDataSource, private val dynamicContentLocalDataSource: LocalDynamicContentDataSource) : IPortfolioRepository {

    override suspend fun getPortfolio(key: String, uid: String): Flow<Resource<Portfolio>> {
        return dataSource.fetchPortfolio(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun getPortfolioFromCache(): Flow<Resource<Portfolio>> {
        return portfolioLocalDataSource.fetchPortfolioFromCache().map { resource ->
            resource
        }
    }

    override suspend fun getDynamicContent(key: String, uid: String): Flow<Resource<DynamicContent>> {
        return dataSource.fetchDynamicContent(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun getDynamicContentFromCache(): Flow<Resource<DynamicContent>> {
        return dynamicContentLocalDataSource.fetchDynamicContentFromCache().map { resource ->
            resource
        }
    }

}