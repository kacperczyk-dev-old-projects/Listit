package com.dawid.listit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawid.listit.data.models.TaskModel

@Dao
interface TaskDao {
    @Query("select * from tasks where list_id = :listId")
    fun getAllTasksFor(listId: Int) : List<TaskModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTask(task: TaskModel)

}