package com.dawid.listit.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dawid.listit.R
import com.dawid.listit.domain.HomeList
import kotlinx.android.synthetic.main.item_list.view.*

//import kotlinx.android.synthetic.main.item_measurement.view.*

class HomeListAdapter(val event: MutableLiveData<HomeListEvent> = MutableLiveData()) :
        ListAdapter<HomeList, HomeListAdapter.HomeListViewHolder>(HomeListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HomeListViewHolder(
            inflater.inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        getItem(position). let {
            holder.name.text = it.listModel.name
            //holder.result.text = "${it.result} ${it.unit}"
            //holder.deleteBtn.setOnClickListener {
                //event.value = MeasurementListEvent.OnDeleteBtnClicked(position)
            //}
            holder.itemView.setOnLongClickListener {_ ->
                event.value = HomeListEvent.OnItemLongPressed(it.listModel.id!!)
                true
            }
        }
    }


    class HomeListViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.listNameTxt
        //val result: TextView = root.measureResultTxt
        //val deleteBtn: ImageButton = root.measureDeleteBtn
    }
}

sealed class HomeListEvent {
    data class OnItemLongPressed(val listId: Int) : HomeListEvent()
}

class HomeListDiffUtilCallback : DiffUtil.ItemCallback<HomeList>() {

    override fun areItemsTheSame(oldItem: HomeList, newItem: HomeList): Boolean {
        return oldItem.listModel.id === newItem.listModel.id
    }

    override fun areContentsTheSame(oldItem: HomeList, newItem: HomeList): Boolean {
        return oldItem == newItem
    }
}