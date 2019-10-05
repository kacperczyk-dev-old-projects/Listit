package com.dawid.listit.ui.addedittask

import dagger.Module
import dagger.Provides

@Module
class AddEditTaskModule {

    @Provides
    fun provideTaskId(activity: AddEditTaskActivity): Int {
        return activity.intent.getIntExtra(EXTRA_TASK_ID, -1)
    }
}