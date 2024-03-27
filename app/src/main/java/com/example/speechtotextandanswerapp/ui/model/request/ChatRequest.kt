package com.example.speechtotextandanswerapp.ui.model.request

import com.example.speechtotextandanswerapp.ui.model.Message

data class ChatRequest(
    val model:String? = "gpt-3.5-turbo",
    val messages:ArrayList<Message>?
)