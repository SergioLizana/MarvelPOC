package com.rivia.software.marvelpoc.domain.models.response

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)