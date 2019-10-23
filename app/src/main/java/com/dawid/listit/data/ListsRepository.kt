package com.dawid.listit.data

import com.airbnb.lottie.model.MutablePair
import com.dawid.listit.data.models.ListModel
import com.dawid.listit.domain.HomeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ListsRepository @Inject constructor(val database: ListItDatabase) {

    val listsCache = mutableMapOf<Int, ListModel>()
    val isDirty = mutableMapOf<Int, Boolean>()

    suspend fun getListById(id: Int): ListModel {
        if(isDirty[id] == false) {
            return getListFromCache(id) ?: return getListFromDatabase(id)
        }
        return getListFromDatabase(id)
    }

    private suspend fun getListFromDatabase(id: Int): ListModel {
        Timber.i("REFRESH CACHE GET LIST DB: $id")
        return withContext(Dispatchers.IO) {
            val list = database.listDao.getListById(id)
            listsCache[id] = list
            isDirty[id] = false
            return@withContext list
        }
    }


    private fun getListFromCache(id: Int?): ListModel? {
        Timber.i("REFRESH CACHE GET CACHE: $id")
        if(id == -1 && listsCache[id] == null) {
            listsCache[id] = ListModel()
        }
        return listsCache[id]
    }

    suspend fun getAllListsWithMetrics(): List<HomeList> {
        return withContext(Dispatchers.IO) {
            database.listDao.getAllListsWithMetrics()
        }
    }

    suspend fun saveList(list: ListModel) {
        withContext(Dispatchers.IO) {
            val newListId = database.listDao.saveList(list).toInt()
            getListFromDatabase(newListId)
            clearCache(-1)
        }
    }

    suspend fun deleteLists(lists: List<ListModel>) {
        withContext(Dispatchers.IO) {
            database.listDao.deleteLists(*lists.toTypedArray())
        }
    }

    fun markAsDirty(id: Int, dirty: Boolean = true) {
        isDirty[id] = dirty
    }

    private fun clearCache(id: Int = -1) {
        Timber.i("REFRESH CACHE: list $id")
        listsCache.remove(id)
    }
}