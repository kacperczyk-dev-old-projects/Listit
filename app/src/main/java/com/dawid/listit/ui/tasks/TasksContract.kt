package com.dawid.listit.ui.tasks

import com.dawid.listit.database.models.TaskModel
import com.dawid.listit.ui.BaseView

interface TasksContract {
    interface View: BaseView<Presenter> {
        fun updateScreen(tasks: List<TaskModel>)
        fun startAddEditTask(taskId: Int)
    }

    interface Presenter {
        fun updateData()
    }
}