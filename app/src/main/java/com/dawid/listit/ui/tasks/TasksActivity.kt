package com.dawid.listit.ui.tasks

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.dawid.listit.R
import com.dawid.listit.database.models.TaskModel
import com.dawid.listit.ui.addeditlist.EXTRA_LIST_ID
import com.dawid.listit.ui.addedittask.AddEditTaskActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_tasks.*

class TasksActivity : DaggerAppCompatActivity(), TasksContract.View {

    private lateinit var adapter: TaskModelListAdapter
    @Inject
    lateinit var presenter: TasksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        addTaskFab.setOnClickListener {
            startAddEditTask(-1)
        }
    }

    override fun startAddEditTask(taskId: Int) {
        val intent = Intent(this, AddEditTaskActivity::class.java)
        intent.putExtra(EXTRA_LIST_ID, taskId)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tasks_menu, menu)
        return true
    }

    override fun onStart() {
        super.onStart()
        setupAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        tasksList.adapter = null
    }

    private fun setupAdapter() {
        adapter = TaskModelListAdapter()
        tasksList.adapter = adapter
//        adapter.event.observe(this, Observer {
//            presenter.handleEvent(it)
//        })
    }

    override fun updateScreen(tasks: List<TaskModel>) {
        adapter.submitList(tasks)
    }
}
