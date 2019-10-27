package com.dawid.listit.ui.addeditlist

import android.os.Bundle
import com.dawid.listit.ui.BaseView

interface AddEditListContract {

    interface View : BaseView<Presenter> {
        fun setBackgroundColor(oldColor: String, color: String)
        fun setListColor(color: String)
        fun setListName(name: String)
        fun setListNotes(notes: String)
    }

    interface Presenter {
        fun init(savedInstanceState: Bundle?)
        fun setListColor(color: String)
        fun setListName(name: String)
        fun setListNotes(notes: String)
        fun saveList()
        fun markAsDirty(dirty: Boolean)
    }

}