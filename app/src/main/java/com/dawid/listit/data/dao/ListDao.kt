package com.dawid.listit.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dawid.listit.data.models.ListModel
import com.dawid.listit.domain.HomeList

@Dao
interface ListDao {
    @Query("select * from lists")
    fun getAllLists() : LiveData<List<ListModel>>

    @Query("select * from lists where id = :id")
    fun getListById(id: Int) : ListModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(list: ListModel): Long

    @Query("""
        select (select count(1) from tasks t where t.list_id = l.id) as 'overdue', 
        (select count(1) from tasks t where t.list_id = l.id) as 'dueToday', 
        * 
        from lists l """)
    fun getAllListsWithMetrics(): List<HomeList>

    @Delete
    fun deleteLists(vararg list: ListModel)
}