package io.chaldeaprjkt.gamespace

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GameSpace : Application() {
    override fun onCreate() {
        super.onCreate()
        if (!isRooted()) {
            Toast.makeText(this, "This app requires root access!", Toast.LENGTH_LONG).show()
            // Exit the app
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    private fun isRooted(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("su -c id")
            process.inputStream.bufferedReader().use { it.readText().contains("uid=0") }
        } catch (e: Exception) {
            false
        }
    }
}
