package za.co.lbnkosi.portfolio.domain.usecase

import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.repository.IPortfolioRepository
import javax.inject.Inject

class PortfolioUseCase @Inject constructor(private val repository: IPortfolioRepository) {

    suspend fun getPortfolio(key: String, uid: String): Flow<Resource<Portfolio>> = repository.getPortfolio(key, uid)

    suspend fun getPortfolioFromCache(): Flow<Resource<Portfolio>> = repository.getPortfolioFromCache()


}