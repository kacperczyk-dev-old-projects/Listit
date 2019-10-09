package com.dawid.listit.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dawid.listit.R
import com.dawid.listit.domain.HomeList
import kotlinx.android.synthetic.main.item_list.view.*
import javax.inject.Inject

//import kotlinx.android.synthetic.main.item_measurement.view.*

class HomeListAdapter @Inject constructor(var appContext: Context)
    : ListAdapter<HomeList, HomeListAdapter.HomeListViewHolder>(HomeListDiffUtilCallback()) {

    val event: MutableLiveData<HomeListEvent> = MutableLiveData()

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
            holder.itemView.listCard.isChecked = false
            holder.name.text = it.listModel.name
            holder.overdue.text = appContext.resources.getString(R.string.number_overdue, it.overdue)
            holder.dueToday.text = appContext.resources.getString(R.string.number_due_today, it.dueToday)

            holder.itemView.listCard.setOnLongClickListener {card ->
                event.value = HomeListEvent.OnItemLongPressed(it.listModel.id!!, card)
                true
            }

            holder.itemView.listCard.setOnClickListener { card ->
                event.value = HomeListEvent.OnItemClicked(it.listModel.id!!, card)
            }
        }
    }


    class HomeListViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.listNameTxt
        val overdue: TextView = root.overdueTxt
        val dueToday: TextView = root.dueTodayTxt
    }
}

sealed class HomeListEvent {
    data class OnItemLongPressed(val listId: Int, val card: View) : HomeListEvent()
    data class OnItemClicked(val listId: Int, val card: View) : HomeListEvent()
}

class HomeListDiffUtilCallback : DiffUtil.ItemCallback<HomeList>() {

    override fun areItemsTheSame(oldItem: HomeList, newItem: HomeList): Boolean {
        return oldItem.listModel.id === newItem.listModel.id
    }

    override fun areContentsTheSame(oldItem: HomeList, newItem: HomeList): Boolean {
        return oldItem.listModel == newItem.listModel
    }
}