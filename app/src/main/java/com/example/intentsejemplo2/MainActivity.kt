package com.example.intentsejemplo2


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentsejemplo2.databinding.MainActivityBinding
import java.util.*

// 1º Valor único para enviar y recibir datos
// Ver apuntes txt para la importación de dependencias
private const val RC_DATE_SELECTION: Int = 1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

    // 11º Callback para llamar a otra actividad esperando respuesta
    // Si hay más llamadas, hay que crear distintos dateSelectionCall y llamarlos con el launch correspondiente (paso 4)
    private val dateSelectionCall =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                extractResult(result.data!!)
                showDate()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        initDate()
        showDate()
    }

    private fun setupViews() {
        binding.btnSelect.setOnClickListener { navigateToDateSelectionActivity() }
    }

    // 2º
    private fun initDate() {
        val calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH) + 1
        year = calendar.get(Calendar.YEAR)
    }

    // 3º El recurso de cadena main_date es un valor con placeholders
    private fun showDate() {
        binding.lblDate.text = getString(R.string.main_date, day, month, year)
    }

    // 4º Primero hay que hacer la segunda pantalla con su companion object para recibir datos
    // Para crear una nueva actividad: CLick derecho al paquete -> new -> activity -> EmptyActivity
    private fun navigateToDateSelectionActivity() {
        val intent = DateSelectionActivity.newIntent(this, day, month, year)
        //12º
        dateSelectionCall.launch(intent)
    }

    // 13º Extrae la información recibida y la procesa
    private fun extractResult(intent: Intent) {

        if (!intent.hasExtra(DateSelectionActivity.EXTRA_DAY) ||
            !intent.hasExtra(DateSelectionActivity.EXTRA_MONTH) ||
            !intent.hasExtra(DateSelectionActivity.EXTRA_YEAR)) {
            throw RuntimeException(
                "DateSelectionActivity must receive day, month and year in result intent")
        }
        day = intent.getIntExtra(DateSelectionActivity.EXTRA_DAY, 0)
        month = intent.getIntExtra(DateSelectionActivity.EXTRA_MONTH, 0)
        year = intent.getIntExtra(DateSelectionActivity.EXTRA_YEAR, 0)

    }


}