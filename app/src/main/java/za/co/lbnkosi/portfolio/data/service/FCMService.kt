package za.co.lbnkosi.portfolio.data.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import za.co.lbnkosi.portfolio.BuildConfig
import za.co.lbnkosi.portfolio.domain.model.Notification
import za.co.lbnkosi.portfolio.domain.model.NotificationSuccess

interface FCMService {

    @Headers(value = ["Authorization:${BuildConfig.FCM_AUTH}", "Content-type:application/json"])
    @POST("send")
    fun sendNotification(@Body body: Notification): Call<NotificationSuccess>

}