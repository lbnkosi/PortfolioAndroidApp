package za.co.lbnkosi.portfolio.data.source.local.dynamicContent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.lbnkosi.portfolio.data.db.DynamicContentDao
import za.co.lbnkosi.portfolio.data.entity.DynamicContentEntity
import za.co.lbnkosi.portfolio.domain.model.DynamicContent
import za.co.lbnkosi.portfolio.domain.model.Resource
import javax.inject.Inject

class LocalDynamicContentDataSource @Inject constructor(private val dynamicContentDao: DynamicContentDao) {

    suspend fun fetchDynamicContentFromCache(): Flow<Resource<DynamicContent>> {
        if (isCacheAvailable()) {
            return flow { emit(Resource.success(fetchCache().dynamicContent, true)) }
        }
        return flow { emit(Resource.error(Pair("", null), null)) }
    }

    suspend fun fetchCache(): DynamicContentEntity {
        return dynamicContentDao.getDynamicContentEntity()
    }

    suspend fun isCacheAvailable(): Boolean {
        return dynamicContentDao.availableDynamicContent() > 0
    }

    suspend fun saveCache(dynamicContentEntity: DynamicContentEntity): DynamicContentEntity {
        dynamicContentDao.saveDynamicContentEntity(dynamicContentEntity)
        return dynamicContentDao.getDynamicContentEntity()
    }

}