package za.co.lbnkosi.portfolio.di.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import za.co.lbnkosi.portfolio.data.service.FCMService
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

object FirebaseModule {

    private const val FCM_URL_NAME = "fcmUrl"
    private const val FCM_URL = "https://fcm.googleapis.com/fcm/"

    @Provides
    @Named(FCM_URL_NAME)
    fun providesFCMUrl(): String {
        return FCM_URL
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
    @Named("FCM_RETROFIT")
    fun providesFcmRetrofit(okHttpClient: OkHttpClient, @Named(FCM_URL_NAME) baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}