package com.dawid.listit.ui.listdetail

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.dawid.listit.R
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_add_edit_list.*
import javax.inject.Inject

const val EXTRA_LIST_ID = "LIST_ID"
const val EXTRA_LIST_NAME = "LIST_NAME"
const val EXTRA_LIST_NOTES = "LIST_NOTES"
const val EXTRA_LIST_COLOR = "LIST_COLOR"

class AddEditListActivity : DaggerActivity(), AddEditListContract.View {

    @Inject
    lateinit var presenter: AddEditListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_list)

        presenter.setView(this)
        presenter.init(savedInstanceState)

        color_picker_view.addOnColorChangedListener {
            val hexColor = it.toHexColor()
            presenter.setListColor(hexColor)
        }

        listNameEdit.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty()) {
                   presenter.setListName(s.toString())
                } else {
                    presenter.setListName("")
                }
            }
        })

        notesEdit.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty()) {
                    presenter.setListNotes(s.toString())
                } else {
                    presenter.setListName("")
                }
            }
        })

        saveListBtn.setOnClickListener {
            presenter.saveList()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXTRA_LIST_NAME, listNameEdit.text.toString())
        outState.putString(EXTRA_LIST_NOTES, notesEdit.text.toString())
        outState.putString(EXTRA_LIST_COLOR, color_picker_view.selectedColor.toHexColor())
        super.onSaveInstanceState(outState)
    }

    override fun setListName(name: String) {
        listNameEdit.setText(name)
    }

    override fun setListNotes(notes: String) {
        notesEdit.setText(notes)
    }

    override fun setListColor(color: String) {
        color_picker_view.setColor(Color.parseColor(color), false)
    }

    override fun setBackgroundColor(color: String) {
        background.setBackgroundColor(Color.parseColor(color))
    }

}

fun Int.toHexColor(): String {
    return String.format("#%06X", 0xFFFFFF and this)
}