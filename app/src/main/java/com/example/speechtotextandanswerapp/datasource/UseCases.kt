package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.request.QuestionTextToVoiceRequest
import okhttp3.MultipartBody.Part
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCases @Inject constructor(private val repo: Repo) {

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