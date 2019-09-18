package com.dawid.listit.ui.listdetail

import android.os.Bundle
import androidx.annotation.Nullable
import com.dawid.listit.database.ListItRepository
import com.dawid.listit.database.models.ListModel
import com.dawid.listit.ui.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AddEditListPresenter @Inject constructor(@Nullable var listId: Int, var repository: ListItRepository)
    : BasePresenter<AddEditListContract.View>(), AddEditListContract.Presenter {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var list: ListModel


    override fun init(savedInstanceState: Bundle?) {
        if(listId != -1) {
            scope.launch {
                list = repository.getListById(listId)
                Timber.i("LIST: ${list.id} ${list.name} ${list.notes} ${list.color}")
                getView()?.setListColor(list.color)
                getView()?.setBackgroundColor("#FFFFFF", list.color)
                getView()?.setListName(list.name)
                getView()?.setListNotes(list.notes)

            }
        } else {
            list = ListModel()
            list.id = null
            getView()?.setListColor(savedInstanceState?.getString(EXTRA_LIST_COLOR) ?: "#FFFFFF")
            getView()?.setBackgroundColor("#FFFFFF", savedInstanceState?.getString(EXTRA_LIST_COLOR) ?: "#FFFFFF")
            getView()?.setListName(savedInstanceState?.getString(EXTRA_LIST_NAME) ?: "")
            getView()?.setListNotes(savedInstanceState?.getString(EXTRA_LIST_NOTES) ?: "")
            getView()?.enableSaveBtn(!list.name.isBlank())
        }
    }

    override fun setListColor(color: String) {
        val oldColor = list.color
        list.color = color
        getView()?.setBackgroundColor(oldColor, color)
    }

    override fun setListName(name: String) {
        list.name = name
    }

    override fun setListNotes(notes: String) {
        list.notes = notes
    }

    override fun saveList() {
        scope.launch {
            repository.saveList(list)
        }
    }
}