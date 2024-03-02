package com.example.speechtotextandanswerapp.ui.model.request

import com.example.speechtotextandanswerapp.ui.model.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class SaveRequestAndResponseRequest(
    val request:String?,
    val response:String?
)