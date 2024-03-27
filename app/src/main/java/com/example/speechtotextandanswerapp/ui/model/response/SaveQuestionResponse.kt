package com.example.speechtotextandanswerapp.ui.model.response

import com.google.gson.annotations.SerializedName

data class SaveQuestionResponse(
    val id:Long,
    val question:String,
    val answer:String,
    val createdTime:String,
    @SerializedName("audioQuestionFileName")
    val audioQuestion:String,
    @SerializedName("audioAnswerFileName")
    val audioAnswer:String
)