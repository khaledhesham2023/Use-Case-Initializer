package com.example.speechtotextandanswerapp.ui.main

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speechtotextandanswerapp.datasource.UseCases
import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import com.example.speechtotextandanswerapp.ui.model.response.ChatResponse
import com.example.speechtotextandanswerapp.ui.model.response.SaveQuestionResponse
import com.example.speechtotextandanswerapp.ui.model.response.SpeechResponse
import com.example.speechtotextandanswerapp.utils.ApiRequestManager
import com.example.speechtotextandanswerapp.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    private val apiRequestManager: ApiRequestManager
) : ViewModel() {

    private var _getChatResponseLiveData = MutableLiveData<ViewState<ChatResponse>>()
    val getChatResponseLiveData: LiveData<ViewState<ChatResponse>>
        get() = _getChatResponseLiveData

    private var _getQuestionsLiveData = MutableLiveData<ViewState<ArrayList<Question>>>()
    val getQuestionsLiveData: MutableLiveData<ViewState<ArrayList<Question>>>
        get() = _getQuestionsLiveData


    private var _convertSpeechToTextLiveData = MutableLiveData<ViewState<SpeechResponse>>()
    val convertSpeechToTextLiveData: LiveData<ViewState<SpeechResponse>>
        get() = _convertSpeechToTextLiveData

    private var _convertTextToSpeechLiveData = MutableLiveData<ViewState<ByteArray>>()
    val convertTextToSpeechLiveData: LiveData<ViewState<ByteArray>>
        get() = _convertTextToSpeechLiveData

    private val _saveQuestionLiveData = MutableLiveData<ViewState<SaveQuestionResponse>>()
    val saveQuestionLiveData:LiveData<ViewState<SaveQuestionResponse>>
        get() = _saveQuestionLiveData


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getChatResponse(request: ChatRequest) {
        viewModelScope.launch {
            apiRequestManager.requestApi(
                { useCases.getChatResponse(request) },
                _getChatResponseLiveData
            )
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getQuestions() = viewModelScope.launch {
        apiRequestManager.requestApi({ useCases.getQuestions() }, _getQuestionsLiveData)

    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun convertSpeechToText(file: MultipartBody.Part, model: MultipartBody.Part) =
        viewModelScope.launch {
            apiRequestManager.requestApi(
                { useCases.convertSpeechToText(file, model) },
                _convertSpeechToTextLiveData
            )
        }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun convertTextToSpeech(request: TextToSpeechRequest) = viewModelScope.launch {
        apiRequestManager.requestApi(
            { useCases.convertTextToSpeech(request) },
            _convertTextToSpeechLiveData
        )
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun saveQuestion(
        answer: MultipartBody.Part,
        answerFile: MultipartBody.Part,
        question: MultipartBody.Part,
        questionFile:MultipartBody.Part,
        request:MultipartBody.Part,
        response:MultipartBody.Part
    ) = viewModelScope.launch {
        apiRequestManager.requestApi({ useCases.saveQuestion(answer, answerFile, question, questionFile, request, response) },_saveQuestionLiveData)
    }
}