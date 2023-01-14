package ru.startandroid.calculatepercent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {

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

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val sharePreference: Preference? = findPreference("share_app_key")
            sharePreference?.setOnPreferenceClickListener {

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My App")
                val playStoreLink = "https://play.google.com/store/apps/details?id=xyz.teamgravity.gotest"
                shareIntent.putExtra(Intent.EXTRA_TEXT, playStoreLink)
                startActivity(Intent.createChooser(shareIntent, "@string/share"))
                true
            }
        }
    }
}