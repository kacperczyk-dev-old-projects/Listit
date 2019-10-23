package com.dawid.listit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lists")
data class ListModel (
    var name: String = "",
    var notes: String = "",
    var color: String = "#FFFFFF"
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}