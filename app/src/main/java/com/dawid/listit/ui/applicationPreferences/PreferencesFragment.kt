package com.dawid.listit.ui.applicationPreferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dawid.listit.R

class PreferencesFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.application_preferences, rootKey)
    }

}