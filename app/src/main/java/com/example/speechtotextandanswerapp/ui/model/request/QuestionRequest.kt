package com.example.speechtotextandanswerapp.ui.model.request

import com.google.gson.annotations.SerializedName

data class QuestionRequest(
    val question: String?,
    val answer:String?,
    val audioFileName:String?,
    val createdTime:String?
)