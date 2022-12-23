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

                if (receivePrice.toInt()*month.toInt()>priceShown.toInt()){
                    resultPercent = (receivePrice.toInt()*month.toInt() - priceShown.toInt())/priceShown.toDouble() * 100
                    resultPercent = String.format("%.2f",resultPercent).toDouble()
                    alert("Foiz Hisoblandi", "$resultPercent %")
                }else{
                    alert("Ogohlantirish","Siz noto'g'ri qiymat kiritdingiz!")
                }
                }else{
                toast("Maydonlarni to`ldiring!")
            }
        }

        binding.btnClear.setOnClickListener{
            if (binding.etPriceShown.text!!.isNotEmpty() || binding.etReceivedPrice.text!!.isNotEmpty() || binding.etMonth.text!!.isNotEmpty()) {
                binding.etPriceShown.setText("")
                binding.etReceivedPrice.setText("")
                binding.etMonth.setText("")
            }else{
                toast("Ma'lumot kiritmagansiz!")
            }
        }

        binding.ivMore.setOnClickListener{
            toast("Clicked more icon")
        }
    }

    private fun alert(titleMessage:String, message:String){
        val alertDialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle(titleMessage)
            .setMessage(message)
            .setPositiveButton("OK") { dialogInterface, _ -> dialogInterface.dismiss() }
            .create()
        alertDialog.show()
    }

    private fun toast(toastMessage: String) {
        makeText(this,toastMessage, LENGTH_SHORT).show()
    }
}