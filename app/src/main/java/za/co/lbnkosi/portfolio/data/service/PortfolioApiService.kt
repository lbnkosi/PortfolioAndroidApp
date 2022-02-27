package za.co.lbnkosi.portfolio.data.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import za.co.lbnkosi.portfolio.domain.model.*

interface PortfolioApiService {

    companion object {
        const val KEY = "key"
        const val UID = "uid"
        const val PORTFOLIO_PATH = "api/v1/portfolio"
        const val DYNAMIC_CONTENT_PATH = "api/v1/dynamicContent"
        const val ADDRESS_PATH = "api/v1/addresses"
        const val COMPETENCY_PATH = "api/v1/competencies"
        const val CONTACT_PATH = "api/v1/contact"
        const val EDUCATION_PATH = "api/v1/education"
        const val LANGUAGE_PATH = "api/v1/languages"
        const val PROJECT_PATH = "api/v1/projects"
        const val SKILL_PATH = "api/v1/skills"
        const val SOCIAL_PATH = "api/v1/socials"
        const val USER_PATH = "api/v1/user"
        const val WORK_PATH = "api/v1/work"
    }

    @GET(PORTFOLIO_PATH)
    fun fetchPortfolio(
        @Query(KEY) key: String,
        @Query(UID) uid: String,
    ): Call<Portfolio>

    @GET(DYNAMIC_CONTENT_PATH)
    fun fetchDynamicContent(
        @Query(KEY) key: String,
        @Query(UID) uid: String
    ): Call<DynamicContent>

}