package com.example.speechtotextandanswerapp.ui.model

import com.google.gson.annotations.SerializedName

data class Usage(
    @SerializedName("prompt_tokens")
    val promptTokens:Long?,
    @SerializedName("completion_tokens")
    val completionTokens:Long?,
    @SerializedName("total_tokens")
    val totalTokens:Long?
)