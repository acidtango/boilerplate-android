package com.example.core

sealed class UiState<T>(val data: T? = null, message: UiText? = null) {
    class Success<T>(data: T?) : UiState<T>(data)
    sealed class Loading(val isLoading: Boolean = false)
    class Error<T>(message: UiText? = null) : UiState<T>(message = message)
}
