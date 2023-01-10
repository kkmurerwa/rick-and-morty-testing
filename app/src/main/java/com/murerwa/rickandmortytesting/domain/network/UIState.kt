package com.murerwa.rickandmortytesting.domain.network

sealed class UIState<out T> {
    data class Success<out T>(
        val value: T,
        var isLoadingMore: Boolean = false,
        var isLoadingMoreError: String = "",
        var isRefreshing: Boolean = false,
        var currentPage: Int = 1
    ) : UIState<T>()
    data class Error(
        val errorMessage: String?,
        val isNetworkError: Boolean = false
    ) : UIState<Nothing>()
    object Loading : UIState<Nothing>()
}

//data class UIState(
//    val isLoading: Boolean = false,
//    val data: Any? = null,
//    val error: String = ""
//)