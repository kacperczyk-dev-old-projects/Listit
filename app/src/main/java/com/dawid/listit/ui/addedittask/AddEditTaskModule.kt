package com.dawid.listit.ui.addedittask

import com.dawid.listit.ui.addeditlist.EXTRA_LIST_ID
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AddEditTaskModule {

    @Provides
    @Named("taskId")
    fun provideTaskId(activity: AddEditTaskActivity): Int {
        return activity.intent.getIntExtra(EXTRA_TASK_ID, -1)
    }

    @Provides
    @Named("listId")
    fun provideListId(activity: AddEditTaskActivity): Int {
        return activity.intent.getIntExtra(EXTRA_LIST_ID, -1)
    }

}