package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import com.example.speechtotextandanswerapp.ui.model.response.ChatResponse
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCases @Inject constructor(private val repo: Repo) {

    suspend fun getChatResponse(request: ChatRequest): ChatResponse = repo.getChatResponse(request)

    suspend fun getQuestions() = repo.getQuestions()

//    suspend fun saveQuestion(request: SaveQuestionRequest) = repo.saveQuestion(request)

    suspend fun convertSpeechToText(file: MultipartBody.Part, model: MultipartBody.Part) =
        repo.convertSpeechToText(file, model)

    suspend fun convertTextToSpeech(request: TextToSpeechRequest): ByteArray =
        repo.convertTextToSpeech(request).bytes()

//    suspend fun saveAudioFiles(voiceFiles: List<MultipartBody.Part>, id: Long) =
//        repo.saveAudioFiles(voiceFiles, id)

    //    suspend fun insertQuestion(voiceFiles: List<MultipartBody.Part>, request: MultipartBody.Part) =
//        repo.insertQuestion(voiceFiles, request)
    suspend fun saveQuestion(
        answer: MultipartBody.Part,
        answerFile: MultipartBody.Part,
        question: MultipartBody.Part,
        questionFile: MultipartBody.Part,
        request: MultipartBody.Part,
        response: MultipartBody.Part
    ) = repo.saveQuestion(answer, answerFile, question, questionFile, request, response)
}