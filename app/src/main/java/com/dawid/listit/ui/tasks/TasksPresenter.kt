package com.dawid.listit.ui.tasks

import com.dawid.listit.database.ListItRepository
import com.dawid.listit.ui.BasePresenter
import javax.inject.Inject

class TasksPresenter @Inject constructor(var repository: ListItRepository)
    : BasePresenter<TasksContract.View>(), TasksContract.Presenter {

}