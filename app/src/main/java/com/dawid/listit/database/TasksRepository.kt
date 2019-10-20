package com.dawid.listit.database

import com.dawid.listit.database.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksRepository @Inject constructor(val database: ListItDatabase) {

    suspend fun saveTask(task: TaskModel) {
        withContext(Dispatchers.IO) {
            database.taskDao.saveTask(task)
        }
    }
    suspend fun getAllTasksWithMetrics(listId: Int): List<TaskModel> {
        return withContext(Dispatchers.IO) {
            database.taskDao.getAllTasksFor(listId)
        }
    }

}