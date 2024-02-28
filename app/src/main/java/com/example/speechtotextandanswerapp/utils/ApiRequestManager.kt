package com.example.speechtotextandanswerapp.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import javax.inject.Inject

class ApiRequestManager
@Inject
constructor(
    @ApplicationContext val context: Context,
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun <T> requestApi(request: suspend () -> T, liveData: MutableLiveData<ViewState<T>>) {
        if (isInternetConnected(context)) {
            try {
                liveData.value = ViewState.Loading
                val response = request.invoke()
                liveData.value = ViewState.Success(response, "Success")
            } catch (e: HttpException) {
                liveData.value = ViewState.Error(e.response()!!.errorBody()!!.string())
            }
        }
    }
}