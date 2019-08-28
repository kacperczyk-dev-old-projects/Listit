package com.dawid.listit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dawid.listit.database.models.NoteModel

@Dao
interface NoteDao {
    @Query("select * from notes")
    fun getAllNotes() : LiveData<List<NoteModel>>
}