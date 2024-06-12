package com.example.suitmediatest.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.R
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.databinding.ActivityThirdScreenBinding
import kotlinx.coroutines.launch

class ThirdScreen : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDisplayHomeAsUpEnabled(true)
            title = "Third Screen"
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)

        }

        val mainViewModel by viewModels<MainViewModel>()

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        lifecycleScope.launch {
            try {
                mainViewModel.getUserFromApi()
            }
            catch (e: Exception) {
                // Logging
                Log.e("ThirdScreen", "Error fetching user data: ${e.message}")

                // Menampilkan Pesan Kesalahan
                Toast.makeText(this@ThirdScreen, "Failed to fetch user data. Please try again later.", Toast.LENGTH_SHORT).show()
                binding.rvUser.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE

            }

        }

        mainViewModel.userData.observe(this) {user ->
            setUserData(user)
        }
        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun setUserData(user: List<DataItem?>?) {
        val adapter = UserAdapter()
        adapter.submitList(user)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
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
}