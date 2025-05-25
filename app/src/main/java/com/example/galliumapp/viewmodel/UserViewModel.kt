package com.example.galliumapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galliumapp.data.AppDatabase
import com.example.galliumapp.data.User
import kotlinx.coroutines.launch

class UserViewModel(private val db: AppDatabase) : ViewModel() {
    fun insertUser(username: String, password: String) {
        viewModelScope.launch {
            db.userDao().insertUser(User(username = username, password = password))
        }
    }

    suspend fun loginUser(username: String, password: String): Boolean {
        return db.userDao().login(username, password) != null
    }
}
