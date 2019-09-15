package com.dawid.listit.di


import com.dawid.listit.ui.home.HomeActivity
import com.dawid.listit.ui.listdetail.AddEditListActivity
import com.dawid.listit.ui.listdetail.AddEditListModule
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [AddEditListModule::class])
    internal abstract fun addEditListActivity(): AddEditListActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    internal abstract fun homeActivity(): HomeActivity

}