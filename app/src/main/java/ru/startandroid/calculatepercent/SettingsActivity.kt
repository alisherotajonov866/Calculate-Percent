package ru.startandroid.calculatepercent

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*

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

    class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener  {
        private lateinit var changeLanguagePreference: ListPreference
        lateinit var sharePreference: Preference

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            sharePreference = findPreference("share_app_key")!!
            changeLanguagePreference = findPreference("change_language_key")!!
            /*
            val preferenceManagerObj = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val defaultValueName = preferenceManagerObj.getString("change_language_key","")
            changeLanguagePreference?.summary = defaultValueName
            */

            sharePreference.setOnPreferenceClickListener {

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My App")
                val playStoreLink = "https://play.google.com/store/apps/details?id=xyz.teamgravity.gotest"
                shareIntent.putExtra(Intent.EXTRA_TEXT, playStoreLink)
                startActivity(Intent.createChooser(shareIntent, "Share App"))
                true
            }

        }

        override fun onResume() {
            super.onResume()
            changeLanguagePreference.summary = changeLanguagePreference.entry.toString()
            preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
        }

        override fun onPause() {
            super.onPause()
            preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            if (key.equals("change_language_key")){
                changeLanguagePreference.summary = changeLanguagePreference.entry.toString()
            }
        }
    }

}