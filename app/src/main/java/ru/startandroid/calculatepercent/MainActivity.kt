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

        binding.apply {
            etPriceShown.addTextChangedListener(NumberTextWatcherForThousand(etPriceShown))
            etPriceReceived.addTextChangedListener(NumberTextWatcherForThousand(etPriceReceived))

            btnCalculate.setOnClickListener {

                var priceShown = etPriceShown.text.toString()
                var priceReceived = etPriceReceived.text.toString()

                if (priceShown.isNotEmpty() && priceReceived.isNotEmpty() && etMonth.text!!.isNotEmpty()) {
                    priceShown = myTextWatcherObj.trimCommaOfString(priceShown)
                    priceReceived = myTextWatcherObj.trimCommaOfString(priceReceived)

                    val priceShownDouble =priceShown.toDouble()
                    val priceReceivedInt = priceReceived.toInt()
                    val month = etMonth.text.toString().toInt()

                    if (priceReceivedInt*month>priceShownDouble){
                        val resultPercent = (priceReceivedInt*month - priceShownDouble)/priceShownDouble * 100
                        alert("Foiz Hisoblandi", "${String.format("%.2f",resultPercent)} %")
                    }else{
                        alert("Ogohlantirish","Noto`g`ri qiymat kiritdingiz!")
                    }

                }else{
                    toast("Maydonlarni to`ldiring!")
                }
            }

            btnClear.setOnClickListener{
                if (etPriceShown.text!!.isNotEmpty() || etPriceReceived.text!!.isNotEmpty() || etMonth.text!!.isNotEmpty()) {
                    etPriceShown.setText("")
                    etPriceReceived.setText("")
                    etMonth.setText("")
                }else{
                    toast("Ma`lumot kiritmagansiz!")
                }
            }
        }

        binding.ivSettings.setOnClickListener{
            val intent = Intent(this,SettingsActivity::class.java)
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