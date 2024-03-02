package com.example.speechtotextandanswerapp.ui.model

import com.google.gson.annotations.SerializedName

data class Question(
    val id:Long?,
    val question:String?,
    val answer:String?,
    val createdTime:String?
)