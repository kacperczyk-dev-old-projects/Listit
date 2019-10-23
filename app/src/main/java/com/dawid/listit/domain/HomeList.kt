package com.dawid.listit.domain

import androidx.room.Embedded
import com.dawid.listit.data.models.ListModel

data class HomeList(
    val overdue: Int,
    val dueToday: Int,
    @Embedded
    val listModel: ListModel
)