package com.example.speechtotextandanswerapp.ui.model.request

data class SaveAudioAnswerRequest(
    val audioQuestionFileName:String?,
    val audioAnswerFileName:String?
)