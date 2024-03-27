package com.example.speechtotextandanswerapp.utils

sealed class ViewState<out T> {
    data object Loading : ViewState<Nothing>()
    class Success<out T>(val data: T, val message: String) : ViewState<T>()
    class Error(val message: String) : ViewState<Nothing>()
}