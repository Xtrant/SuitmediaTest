package com.example.suitmediatest.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //menambahkan action bar untuk menampilkan judul dan tombol back
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDisplayHomeAsUpEnabled(true)
            title = "Second Screen"
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)

        }

        val name = intent.getStringExtra(NAME)
        if (name != null) {
            binding.tvName.text = name
        }

        val username = intent.getStringExtra(USERNAME)
        if (username != null) {
            binding.tvSelectedUsername.text = username
        }

        binding.btnChoose.setOnClickListener {
            startActivity(Intent(this, ThirdScreen::class.java))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {

                @Suppress("DEPRECATION")
                onBackPressed()
                finish()
                return true

            }
        }
        return super.onOptionsItemSelected(item)

    }

    companion object {
        const val NAME = "name"
        const val USERNAME = "username"
    }
}