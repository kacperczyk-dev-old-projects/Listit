package com.dawid.listit.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.dawid.listit.domain.HomeList
import com.dawid.listit.ui.listdetail.AddEditListActivity
import com.dawid.listit.ui.listdetail.EXTRA_LIST_ID
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject
import com.dawid.listit.R
import com.google.android.material.card.MaterialCardView


class HomeActivity : DaggerAppCompatActivity(), HomeContract.View {

    private lateinit var adapter: HomeListAdapter

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

    override fun startActionMode() {
        startActionMode(HomeActionModeCallback())
    }

    override fun setCardChecked(card: View) {
        card as MaterialCardView
        card.isChecked = !card.isChecked
    }

    override fun startAddEdit(listId: Int) {
        val intent = Intent(this, AddEditListActivity::class.java)
        intent.putExtra(EXTRA_LIST_ID, listId)
        startActivity(intent)
    }
}
