package com.example.speechtotextandanswerapp.ui.model

import com.google.gson.annotations.SerializedName

data class Choice(
    val index:Long?,
    val message:Message?,
    val logprobs:String?,
    @SerializedName("finish_reason")
    val finishReason:String?,
)