package com.dawid.listit.ui.addedittask

import com.dawid.listit.database.models.TaskModel
import com.dawid.listit.ui.BaseView

interface AddEditTaskContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter {
        fun saveTask(task: TaskModel)
    }
}