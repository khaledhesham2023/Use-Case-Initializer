package com.example.speechtotextandanswerapp.ui.model.response

import com.example.speechtotextandanswerapp.ui.model.Choice
import com.example.speechtotextandanswerapp.ui.model.Usage
import com.google.gson.annotations.SerializedName

data class ChatResponse(
    val id:String?,
    @SerializedName("object")
    val objectName: String?,
    val created:Long?,
    val model:String?,
    @SerializedName("system_fingerprint")
    val systemFingerprint:String?,
    val choices: ArrayList<Choice>?,
    val usage: Usage?
    )