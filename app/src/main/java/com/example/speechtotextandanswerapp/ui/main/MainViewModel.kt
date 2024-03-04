package com.example.speechtotextandanswerapp.ui.main

import android.content.Context
import android.net.http.HttpException
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speechtotextandanswerapp.datasource.UseCases
import com.example.speechtotextandanswerapp.ui.model.Choice
import com.example.speechtotextandanswerapp.ui.model.Message
import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.QuestionRequest
import com.example.speechtotextandanswerapp.ui.model.request.SaveAudioAnswerRequest
import com.example.speechtotextandanswerapp.ui.model.request.SaveRequestAndResponseRequest
import com.example.speechtotextandanswerapp.ui.model.request.TextToSpeechRequest
import com.example.speechtotextandanswerapp.ui.model.response.BaseResponse
import com.example.speechtotextandanswerapp.ui.model.response.ChatResponse
import com.example.speechtotextandanswerapp.ui.model.response.SpeechResponse
import com.example.speechtotextandanswerapp.utils.ApiRequestManager
import com.example.speechtotextandanswerapp.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context,private val useCases: UseCases, private val apiRequestManager: ApiRequestManager) : ViewModel() {

    private var _getChatResponseLiveData = MutableLiveData<ViewState<ChatResponse>>()
    val getChatResponseLiveData:LiveData<ViewState<ChatResponse>>
        get() = _getChatResponseLiveData

    private var _getQuestionsLiveData = MutableLiveData<ViewState<ArrayList<Question>>>()
    val getQuestionsLiveData : MutableLiveData<ViewState<ArrayList<Question>>>
        get() = _getQuestionsLiveData

    private var _saveQuestionLiveData = MutableLiveData<ViewState<BaseResponse>>()
    val saveQuestionLiveData:LiveData<ViewState<BaseResponse>>
        get() = _saveQuestionLiveData

    private var _getSpeechResponseLiveData = MutableLiveData<ViewState<SpeechResponse>>()
    val getSpeechResponseLiveData:LiveData<ViewState<SpeechResponse>>
        get() = _getSpeechResponseLiveData

    private var _saveRequestAndResponseLiveData = MutableLiveData<ViewState<BaseResponse>>()
    val saveRequestAndResponseLiveData : LiveData<ViewState<BaseResponse>>
        get() = _saveRequestAndResponseLiveData
    private var _convertResponseToSpeechLiveData = MutableLiveData<ViewState<ByteArray>>()
    val convertResponseToSpeechLiveData : LiveData<ViewState<ByteArray>>
        get() = _convertResponseToSpeechLiveData
    private val _saveAudioAnswerLiveData = MutableLiveData<ViewState<BaseResponse>>()
    val saveAudioAnswerLiveData:LiveData<ViewState<BaseResponse>>
        get() = _saveAudioAnswerLiveData


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getChatResponse(request: ChatRequest) {
        viewModelScope.launch {
            apiRequestManager.requestApi({useCases.getChatResponse(request)},_getChatResponseLiveData)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getQuestions() = viewModelScope.launch {
        apiRequestManager.requestApi({useCases.getQuestions()},_getQuestionsLiveData)

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun saveQuestion(request: QuestionRequest) = viewModelScope.launch {
        apiRequestManager.requestApi({useCases.saveQuestion(request)},_saveQuestionLiveData)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getSpeechResponse(file: MultipartBody.Part, model:MultipartBody.Part) = viewModelScope.launch {
            apiRequestManager.requestApi({useCases.getSpeechResponse(file, model)},_getSpeechResponseLiveData)

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun saveRequestAndResponse(audioName:String, request: SaveRequestAndResponseRequest) = viewModelScope.launch {
        apiRequestManager.requestApi({useCases.saveRequestAndResponse(audioName,request)},_saveRequestAndResponseLiveData)
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun convertResponseToSpeech(request: TextToSpeechRequest) = viewModelScope.launch {
        apiRequestManager.requestApi({useCases.convertResponseToSpeech(request)},_convertResponseToSpeechLiveData)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun saveAudioAnswer(request: SaveAudioAnswerRequest) = viewModelScope.launch {
        apiRequestManager.requestApi({useCases.saveAudioAnswer(request)},_saveAudioAnswerLiveData)
    }
}