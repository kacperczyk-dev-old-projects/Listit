package com.dawid.listit.ui.listdetail

import com.dawid.listit.ui.BaseView

interface AddEditListContract {

    interface View : BaseView<Presenter> {
        fun setBackgroundColor(color: String)
    }

    interface Presenter {
        fun setListColor(color: String)
    }

}