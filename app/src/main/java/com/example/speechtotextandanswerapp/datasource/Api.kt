package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.ui.model.QuestionToAnswerEntity
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.QuestionTextToVoiceRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import com.example.speechtotextandanswerapp.ui.model.response.ChatResponse
import com.example.speechtotextandanswerapp.ui.model.response.SaveQuestionResponse
import com.example.speechtotextandanswerapp.ui.model.response.SpeechResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Api {
    @GET("questions")
    suspend fun getQuestionsHistory(): ArrayList<Question>

    @Multipart
    @POST("voice-to-voice")
    suspend fun getVoiceAnswer(@Part questionFile: MultipartBody.Part): ResponseBody

    @POST("question-to-voice")
    suspend fun getVoiceAnswerFromText(@Body request: QuestionTextToVoiceRequest): ResponseBody

    @POST("question-to-answer")
    suspend fun getAnswerTextFromQuestionText(@Body request: QuestionTextToVoiceRequest): Question
}