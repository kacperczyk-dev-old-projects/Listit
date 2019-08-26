package com.dawid.listit.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = ListModel::class,
            parentColumns = ["id"],
            childColumns = ["list_id"])]
)
data class NoteModel (
    @ColumnInfo(name = "list_id")
    var listId: Int,
    var note: String,
    val created: Long,
    var address: String? = null,
    var emaail: String? = null,
    var url: String? = null,
    var reminder: String? = null
)  {
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}