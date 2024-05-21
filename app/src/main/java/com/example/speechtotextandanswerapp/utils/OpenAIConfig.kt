package com.example.speechtotextandanswerapp.utils

import android.content.Context
import com.example.speechtotextandanswerapp.R
import java.io.IOException
import java.util.Properties

object OpenAIConfig {
    fun getBaseUrl(context:Context):String? {
        val properties = Properties()
        return try {
            val inputStream = context.resources.openRawResource(R.raw.openai)
            properties.load(inputStream)
            properties.getProperty("base_url")
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
    }
}