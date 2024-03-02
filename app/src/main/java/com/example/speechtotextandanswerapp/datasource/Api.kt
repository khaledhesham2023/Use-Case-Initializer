package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.Post
import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.QuestionRequest
import com.example.speechtotextandanswerapp.ui.model.request.SaveRequestAndResponseRequest
import com.example.speechtotextandanswerapp.ui.model.response.BaseResponse
import com.example.speechtotextandanswerapp.ui.model.response.ChatResponse
import com.example.speechtotextandanswerapp.ui.model.response.SpeechResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.io.File

interface Api {

    @POST("https://api.openai.com/v1/chat/completions")
    suspend fun getChatResponse(@Body request: ChatRequest): ChatResponse

    @Multipart
    @POST("https://api.openai.com/v1/audio/transcriptions")
    suspend fun getSpeechResponse(
        @Part file: MultipartBody.Part,
        @Part model: MultipartBody.Part
    ): SpeechResponse

    @GET("questions")
    suspend fun getQuestionsHistory(): ArrayList<Question>

    @POST("questions/save")
    suspend fun saveQuestion(@Body request: QuestionRequest): BaseResponse

    @POST("saveRequestAndResponse/{audioName}")
    suspend fun saveRequestAndResponse(@Path("audioName") audioFileName:String,@Body request: SaveRequestAndResponseRequest):BaseResponse
}