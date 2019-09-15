package com.dawid.listit.di

import com.dawid.listit.ListItApplication
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = [
    DatabaseModule::class,
    AndroidInjectionModule::class,
    ActivityBindingModule::class
])
interface AppComponent : AndroidInjector<ListItApplication> {

    @Component.Factory
    interface Builder {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}