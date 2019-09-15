package com.dawid.listit.database

import com.dawid.listit.database.models.ListModel
import com.dawid.listit.domain.HomeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListItRepository @Inject constructor(val database: ListItDatabase) {

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

}

