package com.dawid.listit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawid.listit.database.models.ListModel
import com.dawid.listit.domain.HomeList

@Dao
interface ListDao {
    @Query("select * from lists")
    fun getAllLists() : LiveData<List<ListModel>>

    @Query("select * from lists where id = :id")
    fun getListById(id: Int) : ListModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(list: ListModel)

    @Query("select 10 as 'tasksCount', * from lists")
    fun getAllListsWithMetrics(): List<HomeList>
}