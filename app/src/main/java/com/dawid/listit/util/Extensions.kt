package com.dawid.listit.util

import com.dawid.listit.database.models.ListModel
import com.dawid.listit.domain.HomeList

fun List<HomeList>.asListModelList(): List<ListModel> {
    return map { it.listModel }
}