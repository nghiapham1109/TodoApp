package com.example.noteapp.sharedPreferences

import android.content.Context

class SharePreferencesManager(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("$SHARED_PREFERENCES_NAME", Context.MODE_PRIVATE)

    fun saveUserData(userName: String, data: String) {
        with(sharedPreferences.edit()) {
            putString("$KEY_USER_DATA-$userName", data)
            apply()
        }
    }

    fun getUserData(userName: String): String {
        return sharedPreferences.getString("$KEY_USER_DATA-$userName", "")!!
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "LoginSharedPreferences"
        private const val KEY_USER_DATA = "UserData"
    }
}