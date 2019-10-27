package com.dawid.listit.ui.addeditlist

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.WindowManager
import androidx.core.graphics.ColorUtils
import com.dawid.listit.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.dawid.listit.util.toHexColor
import kotlinx.android.synthetic.main.activity_add_edit_list.*
import kotlinx.android.synthetic.main.activity_add_edit_list.fab
import kotlinx.android.synthetic.main.activity_add_edit_list.toolbar
import kotlinx.android.synthetic.main.activity_add_edit_task.*
import kotlinx.android.synthetic.main.content_add_edit_list.*


class AddEditListActivity : DaggerAppCompatActivity(), AddEditListContract.View {

    @Inject
    lateinit var presenter: AddEditListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

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
                    presenter.setListName(s.toString())
                } else {
                    listNameEdit.error = "List name is required"
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

        fab.setOnClickListener {
            presenter.saveList()
            onBackPressed()
        }

        presenter.setView(this)
        presenter.init(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //TODO: menuInflater.inflate(R.menu.???, menu)
        return true
    }

    override fun onBackPressed() {
        presenter.markAsDirty(true)
        super.onBackPressed()
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

    override fun setBackgroundColor(oldColor: String, color: String) {
        val toolbarAnimator = ObjectAnimator.ofObject(
            toolbar,
            "backgroundColor",
            ArgbEvaluator(),
            Color.parseColor(oldColor),
            Color.parseColor(color)
        )
        val statusBarAnimator = ObjectAnimator.ofObject(
            window,
            "statusBarColor",
            ArgbEvaluator(),
            ColorUtils.blendARGB(Color.parseColor(oldColor), Color.BLACK, 0.2f),
            ColorUtils.blendARGB(Color.parseColor(color), Color.BLACK, 0.2f)
        )
        val animSet = AnimatorSet()
        animSet.duration = 450
        animSet.playTogether(toolbarAnimator, statusBarAnimator)
        animSet.start()
    }

}
