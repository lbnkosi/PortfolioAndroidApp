package za.co.lbnkosi.portfolio.domain.repository

import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.domain.model.*

interface IPortfolioRepository {

    suspend fun getPortfolioFromCache(): Flow<Resource<Portfolio>>

    suspend fun getPortfolio(key: String, uid: String): Flow<Resource<Portfolio>>

    suspend fun getDynamicContent(key: String, uid: String): Flow<Resource<DynamicContent>>

}