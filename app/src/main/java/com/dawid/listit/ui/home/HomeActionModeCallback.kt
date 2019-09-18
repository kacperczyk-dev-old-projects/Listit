package com.dawid.listit.ui.home

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import com.dawid.listit.R

class HomeActionModeCallback : ActionMode.Callback {

    private var mode: ActionMode? = null
    private var title: String? = null
    private var subtitle: String? = null

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        this.mode = mode
        mode.menuInflater.inflate(R.menu.home_menu, menu)
        mode.title = title
        mode.subtitle = subtitle
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        this.mode = null
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        //onActionItemClickListener?.onActionItemClick(item)
        mode.finish()
        return true
    }
}