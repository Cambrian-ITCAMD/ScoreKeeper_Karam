package ca.skaram.scorekeeper

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference

class SettingsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

        private lateinit var mySwitchPreference: SwitchPreference
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.settings_layout, rootKey)
            mySwitchPreference = findPreference("switch_preference_1")!!
            mySwitchPreference.onPreferenceChangeListener = this
        }

        /**
         * Called when a preference has been changed by the user. This is called before the state
         * of the preference is about to be updated and before the state is persisted.
         *
         * @param preference The changed preference
         * @param newValue   The new value of the preference
         * @return `true` to update the state of the preference with the new value
         */
        override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
            if (preference == mySwitchPreference) {
                val switchedOn = newValue as Boolean
                val toastMsg = if (switchedOn) "Save Data mode: ON" else "Save Data mode: OFF"
                Toast.makeText(requireContext(), toastMsg, Toast.LENGTH_LONG).show()
            }
            return true
        }
    }
}