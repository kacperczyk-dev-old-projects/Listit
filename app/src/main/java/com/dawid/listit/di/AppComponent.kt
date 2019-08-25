package com.dawid.listit.di

import android.app.Application
import com.dawid.listit.ListItApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidInjectionModule::class
])
interface AppComponent : AndroidInjector<ListItApplication> {

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}