package com.dawid.listit.ui.listdetail

import dagger.Module
import dagger.Provides

@Module
class AddEditListModule {

    @Provides
    fun provideListId(activity: AddEditListActivity): Int {
        return activity.intent.getIntExtra(EXTRA_LIST_ID, -1)
    }
}