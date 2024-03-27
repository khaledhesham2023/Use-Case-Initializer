package com.example.speechtotextandanswerapp.datasource

import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import okhttp3.MultipartBody
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repo @Inject constructor(private val retrofit: Retrofit) {
    private val api: Api = retrofit.create(Api::class.java)

    suspend fun getChatResponse(request: ChatRequest) = api.getChatResponse(request)

    suspend fun getQuestions() = api.getQuestionsHistory()

//    suspend fun saveQuestion(request: SaveQuestionRequest) = api.saveQuestion(request)

    suspend fun convertSpeechToText(file: MultipartBody.Part, model: MultipartBody.Part) =
        api.convertSpeechToText(file, model)

    suspend fun convertTextToSpeech(request: TextToSpeechRequest) =
        api.convertTextToSpeech(request)

//    suspend fun saveAudioFiles(
//        voiceFiles: List<MultipartBody.Part>,
//        id: Long
//    ) = api.saveAudioFiles(voiceFiles, id)

//    suspend fun insertQuestion(
//        voiceFiles: List<MultipartBody.Part>,
//        request: MultipartBody.Part
//    ) = api.insertQuestion(voiceFiles,request)

    suspend fun saveQuestion(
        answer: MultipartBody.Part,
        answerFile: MultipartBody.Part,
        question: MultipartBody.Part,
        questionFile:MultipartBody.Part,
        request:MultipartBody.Part,
        response:MultipartBody.Part
    ) = api.saveQuestion(answer, answerFile, question, questionFile, request, response)
}