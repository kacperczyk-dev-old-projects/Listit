package com.dawid.listit.ui.tasks

import com.dawid.listit.util.EXTRA_LIST_ID
import dagger.Module
import dagger.Provides

@Module
class TasksModule {

    @Provides
    fun provideListId(activity: TasksActivity): Int {
        return activity.intent.getIntExtra(EXTRA_LIST_ID, -1)
    }
}