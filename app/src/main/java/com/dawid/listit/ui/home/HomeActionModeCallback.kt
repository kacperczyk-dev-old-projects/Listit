package com.dawid.listit.ui.home

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import com.dawid.listit.R

enum class ActionModeFlag {
    NONE, SINGLE_ITEM, MULTIPLE_ITEMS
}
class ActionModeClickListener(
    val onActionClicked: (item: MenuItem) -> Unit,
    val onBackActionClicked: () -> Unit
)

class HomeActionModeCallback(private val actionModeClickListener: ActionModeClickListener) : ActionMode.Callback {

    private var mode: ActionMode? = null
    private var title: String? = null
    private var subtitle: String? = null
    private lateinit var menu: Menu

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        this.mode = mode
        this.menu = menu
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
        actionModeClickListener.onBackActionClicked()
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        actionModeClickListener.onActionClicked(item)
        return true
    }

    fun getMenu(): Menu = menu

    fun destroy() {
        mode?.finish()
        mode = null
    }
}