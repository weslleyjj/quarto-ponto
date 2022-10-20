package com.logique.quartoponto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var ponto1: EditText
    lateinit var ponto2: EditText
    lateinit var ponto3: EditText
    lateinit var quartoPonto: TextView
    lateinit var ponto1Validator: TextView

    // TODO: Fazer validação para texto digitado nos pontos
    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s != null && s.toString() == "08:00") {
                ponto1Validator.text = "Disparou o validator"
            } else {
                ponto1Validator.text = ""
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        ponto1 = findViewById(R.id.ponto1)
        ponto2 = findViewById(R.id.ponto2)
        ponto3 = findViewById(R.id.ponto3)
        quartoPonto = findViewById(R.id.quartoPonto)
        ponto1Validator = findViewById(R.id.ponto1_validator)
        btnCalcular.setOnClickListener {calcularQuartoPonto()}
        ponto1.addTextChangedListener(textWatcher)
    }

    fun calcularQuartoPonto() {

        val tempoTotalNecessarioMinutos =  480
        val hora1 = ponto1.text.split(":")
        val hora2 = ponto2.text.split(":")
        val hora3 = ponto3.text.split(":")

        var tempoPagoHoras = hora2[0].toInt() -  hora1[0].toInt()
        var tempoPagoMinutos = hora2[1].toInt() - hora1[1].toInt()

        tempoPagoMinutos += tempoPagoHoras * 60

        var tempoNecessarioRestante = tempoTotalNecessarioMinutos - tempoPagoMinutos

        var horasNecessarias = tempoNecessarioRestante / 60
        var minutosNecessarios = (tempoNecessarioRestante % 60) + hora3[1].toInt()

        var horaFinal = if (minutosNecessarios > 59) (hora3[0].toInt() + horasNecessarias + (minutosNecessarios % 60)).toString() else (hora3[0].toInt() + horasNecessarias).toString()
        var minutoFinal = minutosNecessarios % 60

        quartoPonto.text = "$horaFinal:$minutoFinal"
    }
}