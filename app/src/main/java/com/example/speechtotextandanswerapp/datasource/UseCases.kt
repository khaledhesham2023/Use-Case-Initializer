package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.Message
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.QuestionRequest
import com.example.speechtotextandanswerapp.ui.model.request.SaveRequestAndResponseRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import com.example.speechtotextandanswerapp.ui.model.response.ChatResponse
import okhttp3.MultipartBody
import java.io.File
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCases @Inject constructor(private val repo: Repo) {

    suspend fun getChatResponse(request: ChatRequest): ChatResponse = repo.getChatResponse(request)

    suspend fun getQuestions() = repo.getQuestions()

    suspend fun saveQuestion(request: QuestionRequest) = repo.saveQuestion(request)

    suspend fun getSpeechResponse(file: MultipartBody.Part, model: MultipartBody.Part) =
        repo.getSpeechResponse(file, model)

    suspend fun saveRequestAndResponse(audioName: String, request: SaveRequestAndResponseRequest) =
        repo.saveRequestAndResponse(audioName, request)

    suspend fun convertResponseToSpeech(request: TextToSpeechRequest) =
        repo.convertResponseToSpeech(request)
}