package com.dawid.listit.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dawid.listit.R
import com.dawid.listit.database.models.TaskModel
import kotlinx.android.synthetic.main.item_task.view.*


class TaskModelListAdapter(val event: MutableLiveData<TaskModelEvent> = MutableLiveData()) :
        ListAdapter<TaskModel, TaskModelListAdapter.TaskModelViewHolder>(TaskModelDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskModelViewHolder(
            inflater.inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskModelViewHolder, position: Int) {
        getItem(position). let {
            holder.name.text = it.name
            //holder.result.text = "${it.result} ${it.unit}"
            //holder.deleteBtn.setOnClickListener {
                //event.value = MeasurementListEvent.OnDeleteBtnClicked(position)
            //}
        }
    }


    class TaskModelViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.taskNameTxt
        //val result: TextView = root.measureResultTxt
        //val deleteBtn: ImageButton = root.measureDeleteBtn
    }
}

sealed class TaskModelEvent {
    //TODO: define events
}

class TaskModelDiffUtilCallback : DiffUtil.ItemCallback<TaskModel>() {

    override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem.id === newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem == newItem
    }
}
