package com.dawid.listit.ui.addeditlist

import com.dawid.listit.util.EXTRA_LIST_ID
import dagger.Module
import dagger.Provides

@Module
class AddEditListModule {

    @Provides
    fun provideListId(activity: AddEditListActivity): Int {
        return activity.intent.getIntExtra(EXTRA_LIST_ID, -1)
    }

}