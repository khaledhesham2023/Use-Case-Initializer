package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.QuestionRequest
import com.example.speechtotextandanswerapp.ui.model.request.SaveRequestAndResponseRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import okhttp3.MultipartBody
import retrofit2.Retrofit
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repo @Inject constructor(private val retrofit: Retrofit) {
    private val api: Api = retrofit.create(Api::class.java)

    suspend fun getChatResponse(request: ChatRequest) = api.getChatResponse(request)

    suspend fun getQuestions() = api.getQuestionsHistory()

    suspend fun saveQuestion(request: QuestionRequest) = api.saveQuestion(request)

    suspend fun getSpeechResponse(file: MultipartBody.Part, model: MultipartBody.Part) =
        api.getSpeechResponse(file, model)

    suspend fun saveRequestAndResponse(
        audioName: String,
        requestAndResponseRequest: SaveRequestAndResponseRequest
    ) = api.saveRequestAndResponse(audioName, requestAndResponseRequest)

    suspend fun convertResponseToSpeech(request: TextToSpeechRequest) =
        api.convertResponseToSpeech(request)

}