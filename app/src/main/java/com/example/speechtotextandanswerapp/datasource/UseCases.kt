package com.example.speechtotextandanswerapp.datasource

import android.content.Context
import com.example.speechtotextandanswerapp.ui.model.request.QuestionTextToVoiceRequest
import okhttp3.MultipartBody.Part
import javax.inject.Inject
import javax.inject.Singleton

class UseCases(context: Context) {

    private val repo = Repo(context)

    suspend fun getQuestions() = repo.getQuestions()

    suspend fun getVoiceAnswer(
        questionFile: Part
    ): ByteArray = repo.getVoiceAnswer(questionFile).bytes()

    suspend fun getVoiceAnswerFromText(
        request:QuestionTextToVoiceRequest
    ):ByteArray = repo.getVoiceAnswerFromText(request).bytes()

    suspend fun getAnswerTextFromQuestionText(
        request: QuestionTextToVoiceRequest
    ) = repo.getAnswerTextFromQuestionText(request)
}