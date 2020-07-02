package com.rivia.software.marvelpoc.domain.models.response

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)