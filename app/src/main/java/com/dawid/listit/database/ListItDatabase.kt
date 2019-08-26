package com.dawid.listit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dawid.listit.database.dao.ListDao
import com.dawid.listit.database.models.ListModel
import com.dawid.listit.database.models.NoteModel
import com.dawid.listit.database.models.TaskModel

@Database(
    entities = [
        ListModel::class,
        TaskModel::class,
        NoteModel::class],
    version = 1,
    exportSchema = false)
abstract class ListItDatabase : RoomDatabase() {
    abstract val listDao: ListDao
}