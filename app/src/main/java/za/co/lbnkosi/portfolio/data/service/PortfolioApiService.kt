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