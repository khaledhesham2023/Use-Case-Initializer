package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.QuestionToAnswerEntity
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.QuestionTextToVoiceRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import okhttp3.MultipartBody
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repo @Inject constructor(private val retrofit: Retrofit) {
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