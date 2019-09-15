package com.dawid.listit.ui.home

import com.dawid.listit.domain.HomeList
import com.dawid.listit.ui.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun updateData(data: List<HomeList>)
        fun startAddEdit(listId: Int)
    }

    interface Presenter {
        fun init()
        fun handleEvent(event: HomeListEvent)
    }

}