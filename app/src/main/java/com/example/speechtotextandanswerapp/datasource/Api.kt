package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
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

    @POST("https://api.openai.com/v1/chat/completions")
    suspend fun getChatResponse(@Body request: ChatRequest): ChatResponse

    @Multipart
    @POST("https://api.openai.com/v1/audio/transcriptions")
    suspend fun convertSpeechToText(
        @Part file: MultipartBody.Part,
        @Part model: MultipartBody.Part
    ): SpeechResponse

    @GET("questions")
    suspend fun getQuestionsHistory(): ArrayList<Question>

//    @POST("questions/save")
//    suspend fun saveQuestion(@Body request: SaveQuestionRequest): SaveQuestionResponse

    @POST("https://api.openai.com/v1/audio/speech")
    suspend fun convertTextToSpeech(@Body request: TextToSpeechRequest): ResponseBody

    @Multipart
    @POST("insert")
    suspend fun saveQuestion(
        @Part answer:MultipartBody.Part,
        @Part answerFile:MultipartBody.Part,
        @Part question:MultipartBody.Part,
        @Part questionFile:MultipartBody.Part,
        @Part request:MultipartBody.Part,
        @Part response:MultipartBody.Part,
    ): SaveQuestionResponse

//    @POST("upload/{id}")
//    @Multipart
//    suspend fun saveAudioFiles(
//        @Part voiceFiles:List<MultipartBody.Part>,
//        @Path("id") id: Long
//    ): BaseResponse

//    @POST("insert")
//    @Multipart
//    suspend fun insertQuestion(
//        @Part voiceFiles: List<MultipartBody.Part>,
//        @Part request: MultipartBody.Part
//    ): SaveQuestionResponse
}