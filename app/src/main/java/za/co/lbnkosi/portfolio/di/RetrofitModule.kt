package za.co.lbnkosi.portfolio.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import za.co.lbnkosi.portfolio.data.service.FCMService
import za.co.lbnkosi.portfolio.data.service.PortfolioApiService
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


object RetrofitModule {

}