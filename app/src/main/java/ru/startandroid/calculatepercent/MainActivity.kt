package ru.startandroid.calculatepercent

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.startandroid.calculatepercent.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    var myTextWatcherObj = NumberTextWatcherForThousand

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etPriceShown.addTextChangedListener(NumberTextWatcherForThousand(binding.etPriceShown))
        binding.etPriceReceived.addTextChangedListener(NumberTextWatcherForThousand(binding.etPriceReceived))

        binding.btnCalculate.setOnClickListener {

            var priceShown = binding.etPriceShown.text.toString()
            var priceReceived = binding.etPriceReceived.text.toString()
            val month = binding.etMonth.text.toString()

            if (priceShown.isNotEmpty() && priceReceived.isNotEmpty() && month.isNotEmpty()) {
                priceShown = myTextWatcherObj.trimCommaOfString(priceShown)
                priceReceived = myTextWatcherObj.trimCommaOfString(priceReceived)

                val priceShownDouble = priceShown.toDouble()
                val priceReceivedInt = priceReceived.toInt()
                val monthInt = month.toInt()

                if (priceReceivedInt*monthInt>priceShownDouble){
                    val resultPercent = (priceReceivedInt*monthInt - priceShownDouble)/priceShownDouble * 100
                    alert("Foiz Hisoblandi", "${String.format("%.2f",resultPercent)} %")
                }else{
                    alert("Ogohlantirish","Noto`g`ri qiymat kiritdingiz!")
                }

            }else{
                toast("Maydonlarni to`ldiring!")
            }
        }

        binding.btnClear.setOnClickListener{
            if (binding.etPriceShown.text!!.isNotEmpty() || binding.etPriceReceived.text!!.isNotEmpty() || binding.etMonth.text!!.isNotEmpty()) {
                binding.etPriceShown.setText("")
                binding.etPriceReceived.setText("")
                binding.etMonth.setText("")
            }else{
                toast("Ma`lumot kiritmagansiz!")
            }
        }

        binding.ivInfo.setOnClickListener{
            val intent = Intent(this,Information::class.java)
            startActivity(intent)
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