package za.co.lbnkosi.portfolio.domain.repository

import kotlinx.coroutines.flow.Flow
import za.co.lbnkosi.portfolio.domain.model.*

interface IPortfolioRepository {

    suspend fun getPortfolioFromCache(): Flow<Resource<Portfolio>>

    suspend fun fetchUser(key: String, uid: String): Flow<Resource<User>>

    suspend fun getPortfolio(key: String, uid: String): Flow<Resource<Portfolio>>

    suspend fun fetchWork(key: String, uid: String): Flow<Resource<ArrayList<Work>>>

    suspend fun fetchSkills(key: String, uid: String): Flow<Resource<ArrayList<Skill>>>

    suspend fun fetchSocials(key: String, uid: String): Flow<Resource<ArrayList<Social>>>

    suspend fun fetchAddress(key: String, uid: String): Flow<Resource<ArrayList<Address>>>

    suspend fun fetchContacts(key: String, uid: String): Flow<Resource<ArrayList<Contact>>>

    suspend fun fetchLanguage(key: String, uid: String): Flow<Resource<ArrayList<Language>>>

    suspend fun fetchProjects(key: String, uid: String): Flow<Resource<ArrayList<Project>>>

    suspend fun fetchEducation(key: String, uid: String): Flow<Resource<ArrayList<Education>>>

    suspend fun fetchCompetencies(key: String, uid: String): Flow<Resource<ArrayList<Competency>>>

}