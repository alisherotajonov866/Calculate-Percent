package ru.startandroid.calculatepercent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class InformationActivity : AppCompatActivity() {

    lateinit var back:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        back = findViewById(R.id.information_ivBack)
        back.setOnClickListener{
            finish()
        }

    }
}