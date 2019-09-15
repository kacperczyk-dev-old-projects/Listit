package com.dawid.listit.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.dawid.listit.R
import com.dawid.listit.domain.HomeList
import com.dawid.listit.ui.listdetail.AddEditListActivity
import com.dawid.listit.ui.listdetail.EXTRA_LIST_ID
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerActivity(), LifecycleOwner, HomeContract.View {

    private val lifecycleRegistry = LifecycleRegistry(this)
    private lateinit var adapter: HomeListAdapter

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //listGrid.setHasFixedSize(true)
        //listGrid.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        //listGrid.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        addListFab.setOnClickListener {
            startAddEdit(-1)
        }

        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.init()
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
        adapter = HomeListAdapter()
        listGrid.adapter = adapter
        adapter.event.observe(this, Observer {
            presenter.handleEvent(it)
        })
    }

    override fun updateData(data: List<HomeList>) {
        adapter.submitList(data)
    }

    override fun startAddEdit(listId: Int) {
        val intent = Intent(this, AddEditListActivity::class.java)
        intent.putExtra(EXTRA_LIST_ID, listId)
        startActivity(intent)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
