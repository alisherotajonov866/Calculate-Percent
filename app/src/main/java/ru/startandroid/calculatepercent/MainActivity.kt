package ru.startandroid.calculatepercent

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.startandroid.calculatepercent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {

            val priceShown = binding.etPriceShown.text.toString()
            val receivePrice = binding.etReceivedPrice.text.toString()
            val month = binding.etMonth.text.toString()
            val resultPercent: Double

            if (priceShown.isNotEmpty() && receivePrice.isNotEmpty() && month.isNotEmpty()) {
                resultPercent = (receivePrice.toInt()*month.toInt() - priceShown.toInt())/priceShown.toDouble() * 100
                alert(resultPercent)
                }else{
                makeText(this,"Maydonlarni to`ldiring!", LENGTH_LONG).show()
            }
        }

        binding.btnClear.setOnClickListener{
            if (binding.etPriceShown.text!!.isNotEmpty() || binding.etReceivedPrice.text!!.isNotEmpty() || binding.etMonth.text!!.isNotEmpty()) {
                binding.etPriceShown.setText("")
                binding.etReceivedPrice.setText("")
                binding.etMonth.setText("")
            }else{
                makeText(this,"Ma'lumot kiritmagansiz!", LENGTH_LONG).show()
            }
        }
    }

    private fun alert(message:Double){
        val alertDialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle("Foiz Hisoblandi")
            .setMessage("$message %")
            .setPositiveButton("OK") { dialogInterface, _ -> dialogInterface.dismiss() }
            .create()
        alertDialog.show()
    }
}