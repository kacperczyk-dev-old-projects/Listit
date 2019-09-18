package com.dawid.listit.ui.home

import android.view.View
import com.dawid.listit.domain.HomeList
import com.dawid.listit.ui.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun updateData(data: List<HomeList>)
        fun startAddEdit(listId: Int)
        fun startActionMode()
        fun setCardChecked(card: android.view.View)
    }

    interface Presenter {
        fun init()
        fun handleEvent(event: HomeListEvent)
    }

}