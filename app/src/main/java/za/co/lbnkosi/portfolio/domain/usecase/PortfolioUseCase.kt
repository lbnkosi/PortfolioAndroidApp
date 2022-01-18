package za.co.lbnkosi.portfolio.domain.usecase

import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.repository.IPortfolioRepository
import javax.inject.Inject

class PortfolioUseCase @Inject constructor(private val repository: IPortfolioRepository) {

    suspend fun fetchUser(key: String, uid: String): Flow<Resource<User>> = repository.fetchUser(key, uid)

    suspend fun fetchWork(key: String, uid: String): Flow<Resource<ArrayList<Work>>> = repository.fetchWork(key, uid)

    suspend fun getPortfolio(key: String, uid: String): Flow<Resource<Portfolio>> = repository.getPortfolio(key, uid)

    suspend fun fetchSkills(key: String, uid: String): Flow<Resource<ArrayList<Skill>>> = repository.fetchSkills(key, uid)

    suspend fun fetchSocials(key: String, uid: String): Flow<Resource<ArrayList<Social>>> = repository.fetchSocials(key, uid)

    suspend fun fetchAddress(key: String, uid: String): Flow<Resource<ArrayList<Address>>> = repository.fetchAddress(key, uid)

    suspend fun fetchProjects(key: String, uid: String): Flow<Resource<ArrayList<Project>>> = repository.fetchProjects(key, uid)

    suspend fun fetchContacts(key: String, uid: String): Flow<Resource<ArrayList<Contact>>> = repository.fetchContacts(key, uid)

    suspend fun fetchLanguage(key: String, uid: String): Flow<Resource<ArrayList<Language>>> = repository.fetchLanguage(key, uid)

    suspend fun fetchEducation(key: String, uid: String): Flow<Resource<ArrayList<Education>>> = repository.fetchEducation(key, uid)

    suspend fun fetchCompetencies(key: String, uid: String): Flow<Resource<ArrayList<Competency>>> = repository.fetchCompetencies(key, uid)

}