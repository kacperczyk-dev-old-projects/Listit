package com.dawid.listit.ui.listdetail

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import com.dawid.listit.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_edit_list.*
import javax.inject.Inject
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator


const val EXTRA_LIST_ID = "LIST_ID"
const val EXTRA_LIST_NAME = "LIST_NAME"
const val EXTRA_LIST_NOTES = "LIST_NOTES"
const val EXTRA_LIST_COLOR = "LIST_COLOR"

class AddEditListActivity : DaggerAppCompatActivity(), AddEditListContract.View {

    @Inject
    lateinit var presenter: AddEditListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_add_edit_list)

        presenter.setView(this)
        presenter.init(savedInstanceState)

        color_picker_view.addOnColorChangedListener {
            val hexColor = it.toHexColor()
            presenter.setListColor(hexColor)
        }

        listNameEdit.editText?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty()) {
                    listNameEdit.error = ""
                    saveListBtn.isEnabled = true
                    presenter.setListName(s.toString())
                } else {
                    listNameEdit.error = "List name is required"
                    saveListBtn.isEnabled = false
                    presenter.setListName("")
                }
            }
        })

        notesEdit.editText?.addTextChangedListener(object: TextWatcher {
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
            onBackPressed()
        }

        cancelBtn.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXTRA_LIST_NAME, listNameEdit.editText?.text.toString())
        outState.putString(EXTRA_LIST_NOTES, notesEdit.editText?.text.toString())
        outState.putString(EXTRA_LIST_COLOR, color_picker_view.selectedColor.toHexColor())
        super.onSaveInstanceState(outState)
    }

    override fun setListName(name: String) {
        listNameEdit.editText?.setText(name)
    }

    override fun setListNotes(notes: String) {
        notesEdit.editText?.setText(notes)
    }

    override fun setListColor(color: String) {
        color_picker_view.setColor(Color.parseColor(color), false)
    }

    override fun enableSaveBtn(enable: Boolean) {
        saveListBtn.isEnabled = enable
    }

    override fun setBackgroundColor(oldColor: String, color: String) {
        val animator = ObjectAnimator.ofObject(
            background,
            "backgroundColor",
            ArgbEvaluator(),
            Color.parseColor(oldColor),
            Color.parseColor(color)
        )
        animator.duration = 450
        animator.start()
    }

}

fun Int.toHexColor(): String {
    return String.format("#%06X", 0xFFFFFF and this)
}