package za.co.lbnkosi.portfolio.data.source.local.portfolio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.lbnkosi.portfolio.data.db.PortfolioDao
import za.co.lbnkosi.portfolio.data.entity.PortfolioEntity
import za.co.lbnkosi.portfolio.data.network.RemoteErrorEmitter
import za.co.lbnkosi.portfolio.domain.model.Portfolio
import za.co.lbnkosi.portfolio.domain.model.Resource
import javax.inject.Inject

class LocalPortfolioDataSource @Inject constructor(private val portfolioDao: PortfolioDao) {

    suspend fun fetchPortfolioFromCache(): Flow<Resource<Portfolio>> {
        if (isCacheAvailable()) {
            return flow { emit(Resource.success(fetchCache().portfolio, true)) }
        }
        return flow { emit(Resource.error(Pair("", null), null)) }
    }

    suspend fun fetchCache(): PortfolioEntity {
        return portfolioDao.getPortfolioEntity()
    }

    suspend fun isCacheAvailable(): Boolean {
        return portfolioDao.availablePortfolio() > 0
    }

    suspend fun saveCache(portfolioEntity: PortfolioEntity): PortfolioEntity {
        portfolioDao.savePortfolioEntity(portfolioEntity)
        return portfolioDao.getPortfolioEntity()
    }

}