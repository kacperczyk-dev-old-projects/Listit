package com.dawid.listit.ui.addeditlist

import android.os.Bundle
import androidx.annotation.Nullable
import com.dawid.listit.database.ListsRepository
import com.dawid.listit.database.models.ListModel
import com.dawid.listit.ui.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditListPresenter @Inject constructor(@Nullable var listId: Int, var repository: ListsRepository)
    : BasePresenter<AddEditListContract.View>(), AddEditListContract.Presenter {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var list: ListModel


    override fun init(savedInstanceState: Bundle?) {
        scope.launch {
           list = repository.getListById(listId)
            getView()?.setListColor(list.color)
            getView()?.setBackgroundColor("#FFFFFF", list.color)
            getView()?.setListName(list.name)
            getView()?.setListNotes(list.notes)
        }
    }

    override fun setListColor(color: String) {
        val oldColor = list.color
        list.color = color
        repository.updateCachedList(list)
        getView()?.setBackgroundColor(oldColor, color)
    }

    override fun setListName(name: String) {
        list.name = name
        repository.updateCachedList(list)
    }

    override fun setListNotes(notes: String) {
        list.notes = notes
        repository.updateCachedList(list)
    }

    override fun saveList() {
        scope.launch {
            repository.saveList(list)
        }
    }

    override fun refreshCache() {
        repository.refreshCache(listId)
    }
}