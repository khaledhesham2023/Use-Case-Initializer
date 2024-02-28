package com.example.speechtotextandanswerapp.ui.model

data class Message(
    val role:String? = "user",
    val content:String?,
)