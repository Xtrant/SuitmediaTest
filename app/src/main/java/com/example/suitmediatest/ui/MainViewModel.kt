package com.example.suitmediatest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.retrofit.ApiConfig

class MainViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userData = MutableLiveData<List<DataItem?>?>()
    val userData: LiveData<List<DataItem?>?> = _userData


    suspend fun getUserFromApi() {
        _isLoading.value = true
        val apiConfig = ApiConfig.getApiService().getUsers()
        _isLoading.value = false
        _userData.value = apiConfig.data
    }
}