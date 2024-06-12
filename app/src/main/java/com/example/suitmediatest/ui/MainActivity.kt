package com.example.suitmediatest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()

        binding.btnCheck.setOnClickListener {
            val inputPalindrome = binding.etPalindrome.text.toString().trim()
            if (isPalindrome(inputPalindrome)) {
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Not Palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            if (name.isNotEmpty()) {
                val intent = Intent(this, SecondScreen::class.java)
                intent.putExtra(SecondScreen.NAME, name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Name Must be filled", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isPalindrome(inp: String): Boolean {
        inp.filter { !it.isWhitespace() }
        var startIndex = 0
        var lastIndex = inp.length - 1

        while (startIndex < lastIndex) {
            if (inp[startIndex] != inp[lastIndex]) {
                return false
            }
            startIndex++
            lastIndex--
        }
        return true
    }
}