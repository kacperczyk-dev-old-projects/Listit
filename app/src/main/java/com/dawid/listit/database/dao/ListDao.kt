package com.dawid.listit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawid.listit.database.models.ListModel

@Dao
interface ListDao {
    @Query("select * from lists")
    fun getAllLists() : LiveData<List<ListModel>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addList(list: ListModel)
}