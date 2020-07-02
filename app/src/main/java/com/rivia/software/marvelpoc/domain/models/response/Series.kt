package com.rivia.software.marvelpoc.domain.models.response

import com.rivia.software.marvelpoc.domain.models.response.ItemXX

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)