package com.dawid.listit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dawid.listit.data.dao.ListDao
import com.dawid.listit.data.dao.NoteDao
import com.dawid.listit.data.dao.TaskDao
import com.dawid.listit.data.models.ListModel
import com.dawid.listit.data.models.NoteModel
import com.dawid.listit.data.models.TaskModel

@Database(
    entities = [
        ListModel::class,
        TaskModel::class,
        NoteModel::class],
    version = 1,
    exportSchema = false
)
abstract class ListItDatabase : RoomDatabase() {
    abstract val listDao: ListDao
    abstract val noteDao: NoteDao
    abstract val taskDao: TaskDao
}