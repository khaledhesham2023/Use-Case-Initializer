package com.example.speechtotextandanswerapp.ui.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("id")
    val id:Long?,
    @SerializedName("question")
    val question:String?,
    @SerializedName("answer")
    val answer:String?
)