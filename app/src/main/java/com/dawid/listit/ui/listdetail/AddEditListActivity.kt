package com.dawid.listit.ui.listdetail

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.dawid.listit.R
import kotlinx.android.synthetic.main.activity_add_edit_list.*
import timber.log.Timber


class AddEditListActivity : Activity(), AddEditListContract.View {

    private var presenter = AddEditListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_list)

        presenter.setView(this)

        color_picker_view.addOnColorChangedListener {
            val hexColor = String.format("#%06X", 0xFFFFFF and it)
            Timber.i("Color = $hexColor")
            presenter.setListColor(hexColor)
        }

    }

    override fun setBackgroundColor(color: String) {
        Timber.i("color background = $color")
        background.setBackgroundColor(Color.parseColor(color))
    }
}
