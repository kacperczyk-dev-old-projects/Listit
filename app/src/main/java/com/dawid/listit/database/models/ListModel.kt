package com.dawid.listit.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lists", primaryKeys = ["id"])
data class ListModel (
    var name: String,
    var notes: String,
    var color: String,
    var type: Int
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}