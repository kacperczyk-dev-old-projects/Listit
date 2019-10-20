package com.dawid.listit.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.dawid.listit.domain.HomeList
import com.dawid.listit.ui.addeditlist.AddEditListActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject
import com.dawid.listit.R
import com.dawid.listit.ui.tasks.TasksActivity
import com.dawid.listit.util.EXTRA_LIST_ID
import com.google.android.material.card.MaterialCardView



class HomeActivity : DaggerAppCompatActivity(), HomeContract.View {

    @Inject
    lateinit var adapter: HomeListAdapter
    private var callback: HomeActionModeCallback? = null
    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addListFab.setOnClickListener {
            startAddEdit(-1)
        }

        presenter.setView(this)
    }

    override fun onResume() {
        presenter.init()
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
        setupAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        listGrid.adapter = null
    }

    private fun setupAdapter() {
//        adapter = HomeListAdapter()
        listGrid.adapter = adapter
        adapter.event.observe(this, Observer {
            presenter.handleEvent(it)
        })
    }

    override fun updateData(data: List<HomeList>) {
        adapter.submitList(data)
    }

    override fun startActionMode() {
        val actionModeClickListener = ActionModeClickListener(
            { presenter.handleActionClicked(it) },
            { exitActionMode() }
        )
        callback = HomeActionModeCallback(
            actionModeClickListener
        )
        startActionMode(callback)
    }

    override fun setCardChecked(card: View) {
        card as MaterialCardView
        card.isChecked = !card.isChecked
        callback?.getMenu()?.getItem(0)?.isVisible = !presenter.multipleChecked()
    }

    override fun startAddEdit(listId: Int) {
        val intent = Intent(this, AddEditListActivity::class.java)
        intent.putExtra(EXTRA_LIST_ID, listId)
        startActivity(intent)
    }

    override fun startTasks(listId: Int) {
        val intent = Intent(this, TasksActivity::class.java)
        intent.putExtra(EXTRA_LIST_ID, listId)
        startActivity(intent)
    }

    override fun exitActionMode() {
        callback?.destroy()
        presenter.onExitActionMode()
        adapter.notifyDataSetChanged()
    }
}
