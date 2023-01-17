package ru.startandroid.calculatepercent

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*

class SettingsActivity : AppCompatActivity () {

    lateinit var settingsBack : ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        settingsBack = findViewById(R.id.settings_ivBack)
        settingsBack.setOnClickListener{
            finish()
        }
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat()  {

        private lateinit var sharePreference: Preference

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            sharePreference = findPreference("key_share_app")!!

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
    }

}