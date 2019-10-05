package com.dawid.listit.di


import com.dawid.listit.ui.home.HomeActivity
import com.dawid.listit.ui.addeditlist.AddEditListActivity
import com.dawid.listit.ui.addeditlist.AddEditListModule
import com.dawid.listit.ui.addedittask.AddEditTaskActivity
import com.dawid.listit.ui.addedittask.AddEditTaskModule
import com.dawid.listit.ui.tasks.TasksActivity
import com.dawid.listit.ui.tasks.TasksModule
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TasksModule::class])
    internal abstract fun tasksActivity(): TasksActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [AddEditTaskModule::class])
    internal abstract fun addEditTaskActivity(): AddEditTaskActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [AddEditListModule::class])
    internal abstract fun addEditListActivity(): AddEditListActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    internal abstract fun homeActivity(): HomeActivity

}