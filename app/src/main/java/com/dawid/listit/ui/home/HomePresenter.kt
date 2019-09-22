package com.dawid.listit.ui.home

import android.view.MenuItem
import com.dawid.listit.database.ListItRepository
import com.dawid.listit.domain.HomeList
import com.dawid.listit.ui.BasePresenter
import com.dawid.listit.util.asListModelList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomePresenter @Inject constructor(var repository: ListItRepository)
    : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var list: List<HomeList>
    private var actionMode = false
    private var checkedList = mutableMapOf<Int, HomeList>()

    override fun init() {
        getData()
    }

    override fun handleEvent(event: HomeListEvent) {
        when(event) {
            is HomeListEvent.OnItemLongPressed -> {
                if(actionMode) {
                    getView()?.exitActionMode()
                } else {
                    actionMode = true
                    checkedList[event.listId] = list.find { it.listModel.id == event.listId }!!
                    getView()?.startActionMode()
                    getView()?.setCardChecked(event.card)
                }
            }
            is HomeListEvent.OnItemClicked -> {
                if(actionMode) {
                    val isItemChecked = checkedList[event.listId] != null
                    if(isItemChecked) {
                        checkedList.remove(event.listId)
                        actionMode = checkedList.isNotEmpty()
                    } else {
                        checkedList[event.listId] = list.find { it.listModel.id == event.listId }!!
                    }
                    getView()?.setCardChecked(event.card)
                    if (!actionMode) getView()?.exitActionMode()
                } else {
                    getView()?.startAddEdit(event.listId)
                }
            }
        }
    }

   private fun getData() {
        scope.launch {
            list = repository.getAllListsWithMetrics()
            getView()?.updateData(list)
        }
    }

    override fun handleActionClicked(item: MenuItem) {
        when(item.title) {
            "delete" -> {
                scope.launch {
                    val itemsToDelete = checkedList.values.toList().asListModelList()
                    repository.deleteLists(itemsToDelete)
                    getView()?.exitActionMode()
                    getData()
                }
            }
            "edit" -> {
                getView()?.startAddEdit(checkedList.keys.toList()[0])
                getView()?.exitActionMode()
            }
        }

    }

    override fun onExitActionMode() {
        actionMode = false
        checkedList.clear()
    }
}
