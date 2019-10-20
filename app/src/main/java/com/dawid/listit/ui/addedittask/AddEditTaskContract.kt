package com.dawid.listit.ui.addedittask

import com.dawid.listit.database.models.TaskModel
import com.dawid.listit.ui.BaseView

interface AddEditTaskContract {
    interface View : BaseView<Presenter> {
        fun updateView(task: TaskModel)
        fun updateDateTime()
    }

    interface Presenter {
        fun init()
        fun saveTask()
        fun setName(name: String)
        fun setNotes(notes: String)
        fun setDueDate(date: String)
        fun setReminder()
        fun setFlag(flag: String)
        fun setCompleted(string: String)
    }
}