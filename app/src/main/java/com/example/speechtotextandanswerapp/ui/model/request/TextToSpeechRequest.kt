package com.example.speechtotextandanswerapp.ui.model.request

data class TextToSpeechRequest(
    val model:String = "tts-1",
    val input:String?,
    val voice:String = "alloy"
)