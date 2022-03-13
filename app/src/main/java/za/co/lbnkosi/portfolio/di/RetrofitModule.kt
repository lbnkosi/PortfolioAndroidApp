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

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL_NAME = "baseUrl"
    private const val BASE_URL = "http://api.lbnkosi.co.za/"

    private const val FCM_URL_NAME = "fcmUrl"
    private const val FCM_URL = "https://fcm.googleapis.com/fcm/"

    @Provides
    @Named(BASE_URL_NAME)
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    @Named(FCM_URL_NAME)
    fun providesFCMUrl(): String {
        return FCM_URL
    }

    @Singleton
    @Provides
    fun providesPortfolioApi(@Named("PORTFOLIO_RETROFIT") retrofit: Retrofit): PortfolioApiService {
        return retrofit.create(PortfolioApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesFCMService(@Named("FCM_RETROFIT") retrofit: Retrofit): FCMService {
        return retrofit.create(FCMService::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    @Singleton
    @Provides
    @Named("PORTFOLIO_RETROFIT")
    fun providesRetrofit(okHttpClient: OkHttpClient, @Named(BASE_URL_NAME) baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("FCM_RETROFIT")
    fun providesFcmRetrofit(okHttpClient: OkHttpClient, @Named(FCM_URL_NAME) baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}