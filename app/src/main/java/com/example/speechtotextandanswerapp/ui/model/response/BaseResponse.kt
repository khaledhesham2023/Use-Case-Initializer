package com.example.speechtotextandanswerapp.ui.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    val status:Boolean?,
    val message:String?
)