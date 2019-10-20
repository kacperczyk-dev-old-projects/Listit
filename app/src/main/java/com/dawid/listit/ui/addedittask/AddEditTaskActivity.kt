package com.dawid.listit.ui.addedittask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.widget.doOnTextChanged
import com.dawid.listit.R
import com.dawid.listit.database.models.TaskModel
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_edit_task.*
import kotlinx.android.synthetic.main.content_add_edit_task.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject




class AddEditTaskActivity : DaggerAppCompatActivity(), AddEditTaskContract.View, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Inject
    lateinit var presenter: AddEditTaskPresenter
    private val calendar: Calendar by lazy {
        Calendar.getInstance()
    }
    lateinit var dateTime: String

    private val datePicker by lazy {
        DatePickerDialog(
            this,
            R.style.ThemeOverlay_MaterialComponents_Dialog,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private val timePicker by lazy {
        TimePickerDialog(
            this,
            R.style.ThemeOverlay_MaterialComponents_Dialog,
            this,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
    }



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

        dueDateCard.setOnClickListener {
            datePicker.show()
        }

        datePicker.setOnDismissListener { dateTime = "" }
        timePicker.setOnDismissListener { dateTime = "" }

        presenter.setView(this)
        presenter.init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //TODO: menuInflater.inflate(R.menu.???, menu)
        return true
    }

    override fun updateView(task: TaskModel) {
        taskNameEditTxt.editText?.setText(task.name)
        taskNotesEditTxt.editText?.setText(task.notes)
        dateTxt.text = task.dueDate?.toString()
        reminderTxt.text = task.reminder?.toString()
        flagTxt.text = task.flag
        completedTxt.text = task.completed?.toString()
    }




    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        dateTime = "$year-$month-$day"
        timePicker.show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minutes: Int) {
        dateTime += "T$hour:$minutes"
        presenter.setDueDate(dateTime)
    }

    override fun updateDateTime() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
