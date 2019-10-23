package com.dawid.listit.util

import com.dawid.listit.data.models.ListModel
import com.dawid.listit.domain.HomeList

fun List<HomeList>.asListModelList(): List<ListModel> {
    return map { it.listModel }
}

fun Int.toHexColor(): String {
    return String.format("#%06X", 0xFFFFFF and this)
}