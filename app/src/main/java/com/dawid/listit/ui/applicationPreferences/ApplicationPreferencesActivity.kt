package com.dawid.listit.ui.applicationPreferences

import android.os.Bundle
import com.dawid.listit.R
import dagger.android.support.DaggerAppCompatActivity


class ApplicationPreferencesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_preferences)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settingsFragment, PreferencesFragment())
            .commit()
    }
}
