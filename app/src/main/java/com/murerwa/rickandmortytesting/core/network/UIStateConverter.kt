package com.murerwa.rickandmortytesting.core.network

import android.app.Application
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.utils.readError
import timber.log.Timber

suspend fun <T> convertToUIState(
    response: NetworkResult<T>,
    app: Application,
    errorMessage: String = "",
    onSuccess: (T) -> Unit = { }
): UIState<T> {

    val error = errorMessage.ifEmpty {
        app.getString(R.string.network_error_request_not_completed)
    }

    return when (response) {
        is NetworkResult.Success -> {
            onSuccess.invoke(response.value)

            UIState.Success(response.value)
        }
        is NetworkResult.Failure -> {
            if (response.isNetworkError) {
                UIState.Error(app.getString(R.string.network_error_no_internet))
            } else {
                if (response.errorBody != null) {
                    val sentError = response.errorBody.readError()

                    Timber.d("Response is Not Null: $sentError")

                    Timber.d("Sent Error: $sentError")

                    if (sentError.isNullOrEmpty()) {
                        UIState.Error(error)
                    } else {
                        UIState.Error(sentError)
                    }

                } else {
                    Timber.d("Response is Null")
                    UIState.Error(error)
                }
            }
        }
    }
}