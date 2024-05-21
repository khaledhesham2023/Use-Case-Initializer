package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import com.example.speechtotextandanswerapp.ui.model.response.ChatResponse
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCases @Inject constructor(private val repo: Repo) {

    suspend fun getQuestions() = repo.getQuestions()

    suspend fun getVoiceAnswer(
        questionFile: Part
    ): ByteArray = repo.getVoiceAnswer(questionFile).bytes()
}