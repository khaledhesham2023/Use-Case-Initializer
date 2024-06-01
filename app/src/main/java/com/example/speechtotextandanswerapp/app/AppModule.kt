package com.example.speechtotextandanswerapp.app

import android.app.Application
import android.content.Context
import com.example.speechtotextandanswerapp.utils.OpenAIConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule : Application() {

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideBaseUrl(@ApplicationContext context: Context): String = OpenAIConfig.getBaseUrl(context)!!

    /**
     * Postponed for updates
     */
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
//        val okHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor {
//            val request = it.request()
////            val url = request.url().toString()
//            it.proceed(request)
////            if (url.contains("chat/completions") || url.contains("audio")) {
////                val newRequest = request.newBuilder().addHeader(
////                    "Authorization",
////                    "Bearer ${OpenAIConfig.getApiSecretKey(context)}"
////                ).build()
////                it.proceed(newRequest)
////            } else {
////                it.proceed(request)
////            }
//        })
//            .connectTimeout(60L, TimeUnit.SECONDS)
//            .readTimeout(60L, TimeUnit.SECONDS)
//            .writeTimeout(60L,TimeUnit.SECONDS)
//            .build()
//        return okHttpClient
//    }
}