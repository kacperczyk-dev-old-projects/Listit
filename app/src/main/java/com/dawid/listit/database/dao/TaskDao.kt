package com.dawid.listit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dawid.listit.database.models.TaskModel

@Dao
interface TaskDao {
    @Query("select * from tasks where list_id = :listId")
    fun getAllTasksFor(listId: Int) : List<TaskModel>
}