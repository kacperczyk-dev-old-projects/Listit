package com.dawid.listit.ui.home

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

    override fun init() {
        scope.launch {
            list = repository.getAllListsWithMetrics()
            getView()?.updateData(list)
        }
    }

    override fun handleEvent(event: HomeListEvent) {
        when(event) {
            is HomeListEvent.OnItemLongPressed -> getView()?.startAddEdit(event.listId)
        }
    }
}
