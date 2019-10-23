package com.dawid.listit.ui.addedittask

import androidx.annotation.Nullable
import com.dawid.listit.data.ListItRepository
import com.dawid.listit.data.models.TaskModel
import com.dawid.listit.ui.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Named

class AddEditTaskPresenter @Inject constructor(
    @Nullable @Named("listId") var listId: Int,
    @Nullable @Named("taskId") var taskId: Int,
    var repository: ListItRepository
) : BasePresenter<AddEditTaskContract.View>(), AddEditTaskContract.Presenter {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var task: TaskModel

    override fun init() {
        task = TaskModel("", listId)
        getView()?.updateView(task)
    }

    override fun setName(name: String) {
        task.name = name
    }

    override fun setNotes(notes: String) {
        task.notes = notes
    }

    override fun setDueDate(date: String) {
        val dateTime = DateTime(date).toDate().time
        task.dueDate = dateTime
        getView()?.updateView(task)
    }

    override fun setReminder() {
        //TODO: handle reminder type
    }

    override fun setFlag(flag: String) {
       task.flag = flag
    }

    override fun setCompleted(string: String) {
        task.completed = 1L
    }

    override fun saveTask() {
        scope.launch {
            repository.saveTask(task)
        }
    }

}