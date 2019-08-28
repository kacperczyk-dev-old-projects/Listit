package com.dawid.listit.di

import android.app.Application
import androidx.room.Room
import com.dawid.listit.database.ListItDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideDatabase(application: Application) : ListItDatabase = Room.databaseBuilder(
        application,
        ListItDatabase::class.java,
        "ListIt"
        ).build()

}