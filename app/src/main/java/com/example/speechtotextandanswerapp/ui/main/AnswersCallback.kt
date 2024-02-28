package com.example.speechtotextandanswerapp.ui.main

import com.example.speechtotextandanswerapp.ui.model.Choice
import com.example.speechtotextandanswerapp.ui.model.Message

interface AnswersCallback {
    fun onAnswerSelected(message: Message)
}