package com.example.speechtotextandanswerapp.ui.model

data class Question(
    val id:Long?,
    val question:String?,
    val answer:String?,
    val createdTime:String?,
    val request:String?,
    val response:String?
)