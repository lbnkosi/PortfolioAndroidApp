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

    override suspend fun fetchAddress(key: String, uid: String): Flow<Resource<ArrayList<Address>>> {
        return dataSource.fetchAddress(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchCompetencies(key: String, uid: String): Flow<Resource<ArrayList<Competency>>> {
        return dataSource.fetchCompetencies(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchContacts(key: String, uid: String): Flow<Resource<ArrayList<Contact>>> {
        return dataSource.fetchContacts(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchEducation(key: String, uid: String): Flow<Resource<ArrayList<Education>>> {
        return dataSource.fetchEducation(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchLanguage(key: String, uid: String): Flow<Resource<ArrayList<Language>>> {
        return dataSource.fetchLanguage(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchProjects(key: String, uid: String): Flow<Resource<ArrayList<Project>>> {
        return dataSource.fetchProjects(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchSkills(key: String, uid: String): Flow<Resource<ArrayList<Skill>>> {
        return dataSource.fetchSkills(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchSocials(key: String, uid: String): Flow<Resource<ArrayList<Social>>> {
        return dataSource.fetchSocials(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchUser(key: String, uid: String): Flow<Resource<User>> {
        return dataSource.fetchUser(key, uid).map { resource ->
            resource
        }
    }

    override suspend fun fetchWork(key: String, uid: String): Flow<Resource<ArrayList<Work>>> {
        return dataSource.fetchWork(key, uid).map { resource ->
            resource
        }
    }

}