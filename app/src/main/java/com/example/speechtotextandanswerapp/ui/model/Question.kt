package com.example.speechtotextandanswerapp.ui.model

data class Question(
    val id: Long?,
    val speechToTextEntity: SpeechToTextEntity?,
    val questionToAnswerEntity: QuestionToAnswerEntity?,
    val textToSpeechEntity: TextToSpeechEntity?,
    val createdTime: String?,
)