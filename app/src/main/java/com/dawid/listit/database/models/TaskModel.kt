package com.dawid.listit.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = ListModel::class,
            parentColumns = ["id"],
            childColumns = ["list_id"]),
        ForeignKey(
            entity = TaskModel::class,
            parentColumns = ["id"],
            childColumns = ["parent_task"]
        )]
)
data class TaskModel (
    var name: String,
    @ColumnInfo(name = "list_id")
    var listId: Int,
    var completed: Long? = null,
    @ColumnInfo(name = "due_date")
    var dueDate: Long? = null,
    var reminder: Long? = null,
    var flag: String? = null,
    @ColumnInfo(name = "parent_task")
    var parentTask: Int? = null,
    var reminderMode: String? = null,
    var created: Long = 0L,
    var notes: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
