package com.dawid.listit.ui.listdetail

import com.dawid.listit.ui.BasePresenter

class AddEditListPresenter : BasePresenter<AddEditListContract.View>(), AddEditListContract.Presenter {
    override fun setListColor(color: String) {
        getView()?.setBackgroundColor(color)
    }
}