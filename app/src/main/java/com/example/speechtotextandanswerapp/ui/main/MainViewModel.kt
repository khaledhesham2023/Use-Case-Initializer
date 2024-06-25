package com.example.speechtotextandanswerapp.ui.main

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speechtotextandanswerapp.datasource.UseCases
import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.ui.model.request.QuestionTextToVoiceRequest
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

    private val _getVoiceAnswerFromText = MutableLiveData<ViewState<ByteArray>>()
    val getVoiceAnswerFromText:LiveData<ViewState<ByteArray>>
        get() = _getVoiceAnswerFromText
    private val _getAnswerTextFromQuestionTextLiveData = MutableLiveData<ViewState<Question>>()
    val getAnswerTextFromQuestionTextLiveData:LiveData<ViewState<Question>>
        get() = _getAnswerTextFromQuestionTextLiveData

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

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getVoiceAnswerFromText(
        request:QuestionTextToVoiceRequest
    ) = viewModelScope.launch {
        apiRequestManager.requestApi({useCases.getVoiceAnswerFromText(request)}, _getVoiceAnswerFromText)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getAnswerTextFromQuestionText(
        request: QuestionTextToVoiceRequest
    ) = viewModelScope.launch {
        apiRequestManager.requestApi({useCases.getAnswerTextFromQuestionText(request)},_getAnswerTextFromQuestionTextLiveData)
    }
}