package com.dawid.listit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawid.listit.database.models.TaskModel

@Dao
interface TaskDao {
    @Query("select * from tasks where list_id = :listId")
    fun getAllTasksFor(listId: Int) : List<TaskModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTask(task: TaskModel)

}