package ru.startandroid.calculatepercent

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.startandroid.calculatepercent.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {

            var priceShown = binding.etPriceShown.text.toString()
            var receivePrice = binding.etReceivedPrice.text.toString()
            val month = binding.etMonth.text.toString()
            var resultPercent: Double

            if (priceShown.isNotEmpty() && receivePrice.isNotEmpty() && month.isNotEmpty()) {
                priceShown = priceShown.replace(",","")
                receivePrice = receivePrice.replace(",","")

                resultPercent = (receivePrice.toInt()*month.toInt() - priceShown.toInt())/priceShown.toDouble() * 100
                resultPercent = String.format("%.2f",resultPercent).toDouble()
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