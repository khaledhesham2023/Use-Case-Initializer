package com.example.speechtotextandanswerapp.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor (@ApplicationContext context: Context) {
    private val sharedPreferences = context.getSharedPreferences("shared-preference",Context.MODE_PRIVATE)
    fun setBaseUrl(baseUrl:String) = sharedPreferences.edit().putString("baseUrl",baseUrl).apply()
    fun getBaseUrl():String? = sharedPreferences.getString("baseUrl","http://192.168.1.8:8080/V1/rest/")
}