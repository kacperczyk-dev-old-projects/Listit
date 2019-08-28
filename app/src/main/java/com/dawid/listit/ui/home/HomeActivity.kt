package com.dawid.listit.ui.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.dawid.listit.R
import com.dawid.listit.ui.listdetail.AddEditListActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listGrid.setHasFixedSize(true)
        listGrid.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        listGrid.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        addListFab.setOnClickListener {
            val intent = Intent(this, AddEditListActivity::class.java)
            startActivity(intent)
        }

    }
}
