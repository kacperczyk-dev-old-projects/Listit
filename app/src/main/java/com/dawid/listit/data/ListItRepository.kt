package com.dawid.listit.data

import com.dawid.listit.data.models.ListModel
import com.dawid.listit.data.models.TaskModel
import com.dawid.listit.domain.HomeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListItRepository @Inject constructor(val database: ListItDatabase) {

    //val list: LiveData<List<HomeList>> by lazy { database.listDao.getAllListsWithMetrics() }

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

    suspend fun getListById(id: Int): ListModel {
        return withContext(Dispatchers.IO) {
            database.listDao.getListById(id)
        }
    }

    suspend fun getAllListsWithMetrics(): List<HomeList> {
        return withContext(Dispatchers.IO) {
            database.listDao.getAllListsWithMetrics()
        }
    }

    suspend fun saveList(list: ListModel) {
        withContext(Dispatchers.IO) {
            database.listDao.saveList(list)
        }
    }

    suspend fun deleteLists(lists: List<ListModel>) {
        withContext(Dispatchers.IO) {
            database.listDao.deleteLists(*lists.toTypedArray())
        }
    }

}

