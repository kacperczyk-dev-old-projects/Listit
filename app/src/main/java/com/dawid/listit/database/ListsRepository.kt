package com.dawid.listit.database

import com.dawid.listit.database.models.ListModel
import com.dawid.listit.database.models.TaskModel
import com.dawid.listit.domain.HomeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.*


@Singleton
class ListsRepository @Inject constructor(val database: ListItDatabase) {

    val listsCache = mutableMapOf<Int, ListModel>()

    suspend fun getListById(id: Int): ListModel {
        val cachedList: ListModel = getListFromCache(id) ?: return getListFromDatabase(id)
        return cachedList
    }

    private suspend fun getListFromDatabase(id: Int): ListModel {
        return withContext(Dispatchers.IO) {
            val list = database.listDao.getListById(id)
            listsCache[list.id!!] = list
            return@withContext list
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
            refreshCache()
        }
    }

    suspend fun deleteLists(lists: List<ListModel>) {
        withContext(Dispatchers.IO) {
            database.listDao.deleteLists(*lists.toTypedArray())
        }
    }

    private fun getListFromCache(id: Int?): ListModel? {
        if(id == -1 && listsCache[id] == null) {
            listsCache[id] = ListModel()
        }
        return listsCache[id]
    }

    fun updateCachedList(list: ListModel) {
        listsCache[list.id ?: -1] = list
    }

    fun refreshCache(id: Int = -1) {
        listsCache.remove(id)
    }
}