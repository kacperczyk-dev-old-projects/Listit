package com.dawid.listit.ui.addedittask

import androidx.annotation.Nullable
import com.dawid.listit.database.ListItRepository
import com.dawid.listit.database.models.TaskModel
import javax.inject.Inject

class AddEditTaskPresenter @Inject constructor(@Nullable var listId: Int, var repository: ListItRepository) : AddEditTaskContract.Presenter {

    override fun saveTask(task: TaskModel) {

    }

}