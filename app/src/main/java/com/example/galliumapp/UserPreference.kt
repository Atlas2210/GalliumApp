package com.example.galliumapp.auth

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthPreferences private constructor(private val context: Context) {
    companion object {
        @Volatile private var instance: AuthPreferences? = null

        fun getInstance(context: Context): AuthPreferences {
            return instance ?: synchronized(this) {
                instance ?: AuthPreferences(context.applicationContext).also { instance = it }
            }
        }

        private const val PREFS_NAME = "auth_prefs_v2"
        private const val USERNAME_KEY = "auth_username"
        private const val PASSWORD_KEY = "auth_password"
    }

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Versión sincrónica
    val currentUsername: String?
        get() = sharedPref.getString(USERNAME_KEY, null)

    val currentPassword: String?
        get() = sharedPref.getString(PASSWORD_KEY, null)

    // Versión Flow (reactiva)
    val usernameFlow: Flow<String?> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == USERNAME_KEY) trySend(sharedPref.getString(USERNAME_KEY, null))
        }

        sharedPref.registerOnSharedPreferenceChangeListener(listener)
        trySend(currentUsername) // Emite valor actual inmediatamente
        awaitClose { sharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    val passwordFlow: Flow<String?> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == PASSWORD_KEY) trySend(sharedPref.getString(PASSWORD_KEY, null))
        }

        sharedPref.registerOnSharedPreferenceChangeListener(listener)
        trySend(currentPassword) // Emite valor actual inmediatamente
        awaitClose { sharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    // Operaciones
    fun saveCredentials(username: String, password: String) {
        sharedPref.edit().apply {
            putString(USERNAME_KEY, username)
            putString(PASSWORD_KEY, password)
            apply()
        }
    }

    fun clearCredentials() {
        sharedPref.edit().apply {
            remove(USERNAME_KEY)
            remove(PASSWORD_KEY)
            apply()
        }
    }

    // Versión con callback (opcional)
    fun getUsername(callback: (String?) -> Unit) {
        callback(currentUsername)
    }
}