package com.dawid.listit.domain

import androidx.room.Embedded
import com.dawid.listit.database.models.ListModel

data class HomeList(
    val tasksCount: Int,
    @Embedded
    val listModel: ListModel
)