package com.dawid.listit.ui.home

import androidx.cardview.widget.CardView
import com.dawid.listit.database.ListItRepository
import com.dawid.listit.domain.HomeList
import com.dawid.listit.ui.BasePresenter
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
        scope.launch {
            list = repository.getAllListsWithMetrics()
            getView()?.updateData(list)
        }
    }

    override fun handleEvent(event: HomeListEvent) {
        when(event) {
            is HomeListEvent.OnItemLongPressed -> {
                val isItemChecked = checkedList[event.listId] != null
                if(!actionMode) getView()?.startActionMode()
                if(isItemChecked) {
                    checkedList.remove(event.listId)
                    actionMode = checkedList.isNotEmpty()
                } else {
                    checkedList[event.listId] = list.find { it.listModel.id == event.listId }!!
                    actionMode = true
                }
                getView()?.setCardChecked(event.card)
            }
            is HomeListEvent.OnItemClicked -> {
                val isItemChecked = checkedList[event.listId] != null
                if(actionMode) {
                    if(isItemChecked) {
                        checkedList.remove(event.listId)
                    } else {
                        checkedList[event.listId] = list.find { it.listModel.id == event.listId }!!
                    }
                    getView()?.setCardChecked(event.card)
                } else {
                    getView()?.startAddEdit(event.listId)
                }
            }

        }
    }
}
