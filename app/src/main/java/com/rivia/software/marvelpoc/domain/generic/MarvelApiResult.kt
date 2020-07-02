package com.rivia.software.marvelpoc.domain.generic

sealed class MarvelApiResult<out T,out K> {
    class Error<K>(val error: K) : MarvelApiResult<Nothing,K>()
    class Success<T>(val data: T?) : MarvelApiResult<T,Nothing>()
}