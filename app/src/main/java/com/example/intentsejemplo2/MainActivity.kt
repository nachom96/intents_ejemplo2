package com.example.intentsejemplo2


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentsejemplo2.databinding.MainActivityBinding
import java.util.*

// 1º Valor único para enviar y recibir datos
private const val RC_DATE_SELECTION: Int = 1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

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

    // 3º
    // TODO Preguntar por R.string.main_date
    @SuppressLint("StringFormatInvalid")
    private fun showDate() {
        binding.lblDate.text = getString(R.string.main_date, day, month, year)
    }

    // 4º Primero hay que hacer la segunda pantalla con su companion object para recibir datos
    // Para crear una nueva actividad: CLick derecho al paquete -> new -> activity -> EmptyActivity
    private fun navigateToDateSelectionActivity() {
        val intent = DateSelectionActivity.newIntent(this, day, month, year)
        startActivityForResult(intent, RC_DATE_SELECTION)
    }

    // 11º
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == RC_DATE_SELECTION && intent != null) {
            extractResult(intent)
            showDate()
        }
    }

    // 12º
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