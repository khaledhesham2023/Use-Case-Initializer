package com.example.speechtotextandanswerapp.datasource

import android.content.Context
import com.example.speechtotextandanswerapp.ui.model.request.QuestionTextToVoiceRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class Repo(context: Context) {
    private val sharedPreferencesManager = SharedPreferencesManager(context)
    private val retrofit = Retrofit.Builder()
        .baseUrl(sharedPreferencesManager.getBaseUrl() ?: "")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: Api = retrofit.create(Api::class.java)
    suspend fun getQuestions() = api.getQuestionsHistory()
    suspend fun getVoiceAnswer(
        questionFile:MultipartBody.Part
    ) = api.getVoiceAnswer(questionFile)
    suspend fun getVoiceAnswerFromText(
        request:QuestionTextToVoiceRequest
    ) = api.getVoiceAnswerFromText(request)
    suspend fun getAnswerTextFromQuestionText(
        request: QuestionTextToVoiceRequest
    ) = api.getAnswerTextFromQuestionText(request)
}