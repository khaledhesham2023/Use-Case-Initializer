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


    private var _getQuestionsLiveData = MutableLiveData<ViewState<ArrayList<Question>>>()
    val getQuestionsLiveData: MutableLiveData<ViewState<ArrayList<Question>>>
        get() = _getQuestionsLiveData

    private val _getVoiceAnswer = MutableLiveData<ViewState<ByteArray>>()
    val getVoiceAnswer: LiveData<ViewState<ByteArray>>
        get() = _getVoiceAnswer

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getQuestions() = viewModelScope.launch {
        apiRequestManager.requestApi({ useCases.getQuestions() }, _getQuestionsLiveData)

    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getVoiceAnswer(
        questionFile: MultipartBody.Part
    ) = viewModelScope.launch {
        apiRequestManager.requestApi({ useCases.getVoiceAnswer(questionFile) }, _getVoiceAnswer)
    }
}