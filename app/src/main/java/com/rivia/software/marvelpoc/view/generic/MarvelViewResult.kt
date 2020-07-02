package com.rivia.software.marvelpoc.view.generic

sealed class MarvelViewResult<out T,out K> {
    object Loading : MarvelViewResult<Nothing,Nothing>()
    class Error<K>(val error: K) : MarvelViewResult<Nothing,K>()
    class Success<T>(val data: T?) : MarvelViewResult<T,Nothing>()
}