package com.dawid.listit.ui.tasks

import androidx.annotation.Nullable
import com.dawid.listit.database.ListItRepository
import com.dawid.listit.database.models.TaskModel
import com.dawid.listit.ui.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksPresenter @Inject constructor(@Nullable var listId: Int, var repository: ListItRepository)
    : BasePresenter<TasksContract.View>(), TasksContract.Presenter {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var tasks: List<TaskModel>

    override fun updateData() {
        scope.launch {
            tasks = repository.getAllTasksWithMetrics(listId)
            getView()?.updateScreen(tasks)
        }
    }

}