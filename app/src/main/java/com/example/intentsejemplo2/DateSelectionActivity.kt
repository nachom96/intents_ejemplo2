package com.example.intentsejemplo2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.intentsejemplo2.databinding.DateSelectionActivityBinding
import com.example.intentsejemplo2.utils.hideSoftKeyboard

class DateSelectionActivity : AppCompatActivity() {

    private var day: Int = 0
    private var year: Int = 0
    private var month: Int = 0

    // 5º Recibe el intent y extrae los datos que han sido enviados
    companion object {

        const val EXTRA_DAY = "EXTRA_DAY"
        const val EXTRA_MONTH = "EXTRA_MONTH"
        const val EXTRA_YEAR = "EXTRA_YEAR"

        fun newIntent(context: Context, day: Int, month: Int, year: Int): Intent =
            Intent(context, DateSelectionActivity::class.java)
                .putExtras(bundleOf(EXTRA_DAY to day, EXTRA_MONTH to month,
                    EXTRA_YEAR to year))
    }

    private lateinit var binding: DateSelectionActivityBinding

    // 6ª
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DateSelectionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupViews()
    }

    // 7ª
    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(EXTRA_DAY) || !intent.hasExtra(EXTRA_MONTH) ||
            !intent.hasExtra(EXTRA_YEAR)) {
            throw RuntimeException(
                "DateSelectionActivity needs to receive day, month and year as extras")
        }
        day = intent.getIntExtra(EXTRA_DAY, 0)
        month = intent.getIntExtra(EXTRA_MONTH, 0)
        year = intent.getIntExtra(EXTRA_YEAR, 0)
    }

    // 8ª Se le da a los textviews el valor recibido
    private fun setupViews() {
        binding.txtDay.setText(day.toString())
        binding.txtMonth.setText(month.toString())
        binding.txtYear.setText(year.toString())
        // 10º
        binding.btnAccept.setOnClickListener { btnAcceptOnClick() }

    }

    // 9ª
    private fun btnAcceptOnClick() {
        val day = binding.txtDay.text.toString()
        val month = binding.txtMonth.text.toString()
        val year = binding.txtYear.text.toString()
        binding.txtDay.hideSoftKeyboard()

        // TODO IS VALID FORM
        // (está en el repositorio intents ejemplo)

        setActivityResult(day.toInt(), month.toInt(), year.toInt())
        finish()
    }

    // 10º
    private fun setActivityResult(day: Int, month: Int, year: Int) {
        val result = Intent().putExtras(
            bundleOf(EXTRA_DAY to day, EXTRA_MONTH to month, EXTRA_YEAR to year))
        setResult(RESULT_OK, result)
    }









}