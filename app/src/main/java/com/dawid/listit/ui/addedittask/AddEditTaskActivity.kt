package com.dawid.listit.ui.addedittask

import android.os.Bundle
import android.view.Menu
import androidx.core.widget.doOnTextChanged
import com.dawid.listit.R
import com.dawid.listit.database.models.TaskModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_edit_task.*
import kotlinx.android.synthetic.main.content_add_edit_task.*
import javax.inject.Inject


const val EXTRA_TASK_ID = "TASK_ID"

class AddEditTaskActivity : DaggerAppCompatActivity(), AddEditTaskContract.View {

    @Inject
    lateinit var presenter: AddEditTaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        fab.setOnClickListener { view ->
            presenter.saveTask()
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }

        taskNameEditTxt.editText?.doOnTextChanged { text, start, count, after ->
            presenter.setName(text.toString())
        }
        taskNotesEditTxt.editText?.doOnTextChanged { text, start, count, after ->
            presenter.setNotes(text.toString())
        }

        presenter.setView(this)
        presenter.init()
    }

    override fun updateView(task: TaskModel) {
        taskNameEditTxt.editText?.setText(task.name)
        taskNotesEditTxt.editText?.setText(task.notes)
        dateTxt.text = task.dueDate?.toString()
        reminderTxt.text = task.reminder?.toString()
        flagTxt.text = task.flag
        completedTxt.text = task.completed?.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //TODO: menuInflater.inflate(R.menu.???, menu)
        return true
    }

}
