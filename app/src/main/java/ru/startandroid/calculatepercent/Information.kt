package ru.startandroid.calculatepercent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Information : AppCompatActivity() {
    lateinit var ivBack:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        ivBack = findViewById(R.id.information_ivBack)
        ivBack.setOnClickListener{
            finish()
        }
    }
}