package za.co.lbnkosi.portfolio.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse
import za.co.lbnkosi.portfolio.data.service.PortfolioApiService
import za.co.lbnkosi.portfolio.domain.model.*
import javax.inject.Inject

class PortfolioDataSource @Inject constructor(private val apiService: PortfolioApiService) {

    suspend fun fetchPortfolio(key: String, uid: String): Flow<Resource<Portfolio>> {
        val response = apiService.fetchPortfolio(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchUser(key: String, uid: String): Flow<Resource<User>> {
        val response = apiService.fetchUser(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchAddress(key: String, uid: String): Flow<Resource<ArrayList<Address>>> {
        val response = apiService.fetchAddress(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchCompetencies(key: String, uid: String): Flow<Resource<ArrayList<Competency>>> {
        val response = apiService.fetchCompetencies(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchContacts(key: String, uid: String): Flow<Resource<ArrayList<Contact>>> {
        val response = apiService.fetchContacts(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchEducation(key: String, uid: String): Flow<Resource<ArrayList<Education>>> {
        val response = apiService.fetchEducation(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchLanguage(key: String, uid: String): Flow<Resource<ArrayList<Language>>> {
        val response = apiService.fetchLanguage(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchProjects(key: String, uid: String): Flow<Resource<ArrayList<Project>>> {
        val response = apiService.fetchProjects(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchSkills(key: String, uid: String): Flow<Resource<ArrayList<Skill>>> {
        val response = apiService.fetchSkills(key, uid).awaitResponse()
        val (a, b) = Pair("", "x")

        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchSocials(key: String, uid: String): Flow<Resource<ArrayList<Social>>> {
        val response = apiService.fetchSocials(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

    suspend fun fetchWork(key: String, uid: String): Flow<Resource<ArrayList<Work>>> {
        val response = apiService.fetchWork(key, uid).awaitResponse()
        return if (response.isSuccessful) {
            flow { emit(Resource.success(response.body())) }
        } else {
            flow { emit(Resource.error(Pair(response.message(), response.errorBody()), null)) }
        }
    }

}