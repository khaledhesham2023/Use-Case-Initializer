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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule : Application() {

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideBaseUrl(): String = "http://192.168.1.5:8080/V1/rest/"

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor {
            val request = it.request()
            val url = request.url().toString()
            if (url.contains("chat/completions") || url.contains("audio")) {
                val newRequest = request.newBuilder().addHeader(
                    "Authorization",
                    "Bearer ${OpenAIConfig.getApiSecretKey(context)}"
                ).build()
                it.proceed(newRequest)
            } else {
                it.proceed(request)
            }
        }).build()
        return okHttpClient
    }
}