package com.dawid.listit.ui.addeditlist

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


const val IS_CACHE_DIRTY = "IS_CACHE_DIRTY"

class AddEditListActivity : DaggerAppCompatActivity(), AddEditListContract.View {

    @Inject
    lateinit var presenter: AddEditListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_add_edit_list)

        color_picker_view.addOnColorChangedListener {
            val hexColor = it.toHexColor()
            presenter.setListColor(hexColor)
        }

        listNameEdit.editText?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty()) {
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
                if(s.toString().isNotEmpty()) {
                    presenter.setListNotes(s.toString())
                } else {
                    presenter.setListNotes("")
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

        presenter.setView(this)
        presenter.init(savedInstanceState)
    }

    override fun onBackPressed() {
        presenter.refreshCache()
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IS_CACHE_DIRTY, true)
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