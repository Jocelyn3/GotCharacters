package com.example.characters.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.characters.data.model.GotCharacter

object Util {
    const val GOT_CHARACTERS_DATABASE = "got_characters_database"
    const val CHARACTER_TABLE = "character_table"
    const val CHARACTERS_ENDPOINT = "Characters"
    const val BASE_URL = "https://thronesapi.com/api/v2/"
    val list: List<GotCharacter> = listOf(
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
        GotCharacter(0, "Daenerys", "Targaryen", "Daenerys Targaryen", "Mother of Dragons", "House Targaryen", "https://thronesapi.com/assets/images/daenerys.jpg"),
    )

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}