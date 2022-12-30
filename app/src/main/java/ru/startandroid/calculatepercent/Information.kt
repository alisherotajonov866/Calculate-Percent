package ru.startandroid.calculatepercent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Information : AppCompatActivity() {
    private lateinit var ivBack:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        ivBack = findViewById(R.id.ivBack)
        ivBack.setOnClickListener{
            finish()
        }
    }
}