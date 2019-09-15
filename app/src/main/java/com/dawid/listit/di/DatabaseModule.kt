package com.dawid.listit.di

import android.content.Context
import androidx.room.Room
import com.dawid.listit.database.ListItDatabase
import com.dawid.listit.database.ListItRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideDatabase(application: Context) : ListItDatabase = Room.databaseBuilder(
        application,
        ListItDatabase::class.java,
        "ListIt"
        ).build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideRepository(database: ListItDatabase) : ListItRepository = ListItRepository(database)

}