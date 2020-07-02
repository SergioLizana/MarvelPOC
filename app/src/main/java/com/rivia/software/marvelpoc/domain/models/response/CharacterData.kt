package com.rivia.software.marvelpoc.domain.models.response

data class CharacterData(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)