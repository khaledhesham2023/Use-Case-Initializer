package com.example.speechtotextandanswerapp.ui.model

import com.google.gson.annotations.SerializedName

data class Post(
    val userId:Long?,
    val id:Long?,
    @SerializedName("title")
    val titleString:String?,
    val body:String?
)